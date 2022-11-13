package com.binbini.imall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binbini.imall.contacts.RedisContacts;
import com.binbini.imall.mapper.TbLikeContentMapper;
import com.binbini.imall.mapper.TbLikeMapper;
import com.binbini.imall.pojo.TbLike;
import com.binbini.imall.pojo.TbLikeContent;
import com.binbini.imall.service.LikeService;
import com.binbini.imall.vo.LikeCountVo;
import com.binbini.imall.vo.LikeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: BinBin
 * @Date: 2022/11/12/11:04
 * @Description:
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private TbLikeMapper tbLikeMapper;
    @Autowired
    private TbLikeContentMapper tbLikeContentMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public int likeStatus(Integer commentId, Integer likeUserId) {
        TbLike tbLike = tbLikeMapper.selectOne(new QueryWrapper<TbLike>().eq("comment_id", commentId).eq("like_user_id", likeUserId));
        if (redisTemplate.opsForHash().hasKey(RedisContacts.MAP_KEY_USER_LIKED, RedisContacts.getLikedKey(commentId, likeUserId))) {
            String data = redisTemplate.opsForHash().get(RedisContacts.MAP_KEY_USER_LIKED, RedisContacts.getLikedKey(commentId, likeUserId)).toString();
            if ("1".equals(data)) {
                unLike(commentId, likeUserId);
                return 0;
            }
            if ("0".equals(data)) {
                like(commentId, likeUserId);
                return 1;
            }
        }
        if (tbLike == null) {
            TbLike newTbLike = new TbLike();
            newTbLike.setComment_id(commentId)
                    .setLike_user_id(likeUserId)
                    .setCreated(new Date())
                    .setUpdated(new Date());
            tbLikeMapper.insert(newTbLike);
            like(commentId, likeUserId);
            return 1;
        }
        if (tbLike.getStatus() == 1) {
            unLike(commentId, likeUserId);
            return 0;
        }
        if (tbLike.getStatus() == 0) {
            like(commentId, likeUserId);
            return 1;
        }
        return -1;
    }

    @Override
    public List<LikeVo> getLikedData(Integer commentId) {
        getLikedDataFromRedis();
        QueryWrapper<TbLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", commentId);
        List<TbLike> list = tbLikeMapper.selectList(queryWrapper);
        List<LikeVo> result = new ArrayList<>();
        for (TbLike item:list) {
            LikeVo likeVo = new LikeVo();
            likeVo.setCommentId(item.getComment_id())
                    .setLikeUserId(item.getLike_user_id())
                    .setStatus(item.getStatus());
            result.add(likeVo);
        }
        return result;
    }

    @Override
    public List<LikeCountVo> getLikedCountData(Integer commentId) {
        getLikedCountFromRedis();
        QueryWrapper<TbLikeContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", commentId);
        List<TbLikeContent> list = tbLikeContentMapper.selectList(queryWrapper);
        List<LikeCountVo> result = new ArrayList<>();
        for (TbLikeContent item:list) {
            LikeCountVo likeCountVo = new LikeCountVo();
            likeCountVo.setCommentId(item.getComment_id())
                            .setValue(item.getLike_number());
            result.add(likeCountVo);
        }
        return result;
    }

    private void getLikedDataFromRedis() {
        Cursor<Map.Entry<Object, Object>> scan = redisTemplate.opsForHash().scan(RedisContacts.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<LikeVo> list = new ArrayList<>();
        while (scan.hasNext()) {
            Map.Entry<Object, Object> entry = scan.next();
            String key = (String) entry.getKey();
            String[] split = key.split("::");
            Integer infoId = Integer.parseInt(split[0]);
            Integer likeUserId = Integer.parseInt(split[1]);
            Integer value = (Integer) entry.getValue();

            LikeVo likeVo = new LikeVo();
            likeVo.setCommentId(infoId)
                    .setLikeUserId(likeUserId)
                    .setStatus(value);
            list.add(likeVo);
            //存到 list 后从 Redis 中删除,并写入数据库
            for (LikeVo item:list) {
                TbLike tbLike = tbLikeMapper.selectOne(new QueryWrapper<TbLike>().eq("comment_id", item.getCommentId()).eq("like_user_id", item.getLikeUserId()));
                tbLike.setStatus(item.getStatus());
                tbLikeMapper.updateById(tbLike);
            }
            redisTemplate.opsForHash().delete(RedisContacts.MAP_KEY_USER_LIKED, key);
        }
    }

    private void getLikedCountFromRedis() {
        Cursor<Map.Entry<Object, Object>> scan = redisTemplate.opsForHash().scan(RedisContacts.MAP_KEY_COMMENT_LIKED_COUNT, ScanOptions.NONE);
        List<LikeCountVo> list = new ArrayList<>();
        while (scan.hasNext()) {
            Map.Entry<Object, Object> entry = scan.next();
            Integer key = (Integer) entry.getKey();
            Long value = redisTemplate.opsForHash().increment(RedisContacts.MAP_KEY_COMMENT_LIKED_COUNT, key, 0);

            LikeCountVo likeCountVo = new LikeCountVo();
            likeCountVo.setCommentId(key)
                    .setValue(Math.toIntExact(value));
            list.add(likeCountVo);
            //存到 list 后从 Redis 中删除,并写入数据库
            for (LikeCountVo item:list) {
                TbLikeContent tbLikeContent = tbLikeContentMapper.selectOne(new QueryWrapper<TbLikeContent>().eq("comment_id", item.getCommentId()));
                tbLikeContent.setLike_number(tbLikeContent.getLike_number() + item.getValue());
                tbLikeContentMapper.updateById(tbLikeContent);
            }
            redisTemplate.opsForHash().delete(RedisContacts.MAP_KEY_COMMENT_LIKED_COUNT, key);
        }
    }

    private void like(Integer commentId, Integer likeUserId) {
        String likedKey = RedisContacts.getLikedKey(commentId, likeUserId);
        redisTemplate.opsForHash().increment(RedisContacts.MAP_KEY_COMMENT_LIKED_COUNT, commentId, 1);
        redisTemplate.opsForHash().put(RedisContacts.MAP_KEY_USER_LIKED, likedKey, 1);
    }

    private void unLike(Integer commentId, Integer likeUserId) {
        String likedKey = RedisContacts.getLikedKey(commentId, likeUserId);
        redisTemplate.opsForHash().increment(RedisContacts.MAP_KEY_COMMENT_LIKED_COUNT, commentId, -1);
        redisTemplate.opsForHash().put(RedisContacts.MAP_KEY_USER_LIKED, likedKey, 0);
    }
}
