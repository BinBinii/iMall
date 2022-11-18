package com.binbini.imall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binbini.imall.mapper.TbItemCollectionMapper;
import com.binbini.imall.mapper.TbItemMapper;
import com.binbini.imall.pojo.TbItem;
import com.binbini.imall.pojo.TbItemCollection;
import com.binbini.imall.service.ItemCollectionService;
import com.binbini.imall.utils.RedisUtil;
import com.binbini.imall.vo.CollectionVo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: BinBin
 * @Date: 2022/11/13/15:52
 * @Description:
 */
@Service
public class ItemCollectionServiceImpl implements ItemCollectionService {

    @Autowired
    private TbItemCollectionMapper tbItemCollectionMapper;
    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${collection.pre}")
    private String COLLECTION_PRE;

    @Override
    public int collection(Integer userId, Integer productId, Integer status) {
        // 改变商品的收藏数量
        TbItemCollection tbItemCollection = tbItemCollectionMapper.selectById(productId);
        Integer originCount = tbItemCollection.getCollection_count();
        if (status == 1) {
            tbItemCollection.setCollection_count(originCount - 1);
        }
        if (status == 0) {
            tbItemCollection.setCollection_count(originCount + 1);
        }
        tbItemCollectionMapper.updateById(tbItemCollection);

        // 查询redis内是否存在
        boolean exists = redisUtil.hasKey(COLLECTION_PRE + ":" + userId + "");
        // 如存在则改变数据
        if (exists) {
            String json = String.valueOf(redisUtil.get(COLLECTION_PRE + ":" + userId + ""));
            if (json != null) {
                CollectionVo collectionVo = new Gson().fromJson(json, CollectionVo.class);
                List<Integer> productList = collectionVo.getProductList();
                for (Integer product:productList) {
                    if (productId.equals(product)) {
                        productList.remove(product);
                        redisUtil.set(COLLECTION_PRE + ":" + userId + "", new Gson().toJson(collectionVo));
                        return 0;
                    }
                }
                productList.add(productId);
                collectionVo.setProductList(productList);
                redisUtil.set(COLLECTION_PRE + ":" + userId + "", new Gson().toJson(collectionVo));
                return 1;
            } else {
                return -1;
            }
        }
        // 如果不存在则新增收藏集合
        List<Integer> collectionProductList = new ArrayList<>();
        collectionProductList.add(productId);
        CollectionVo collectionVo = new CollectionVo();
        collectionVo.setUser_id(userId)
                .setProductList(collectionProductList);
        redisUtil.set(COLLECTION_PRE + ":" + userId + "", new Gson().toJson(collectionVo));
        return 1;
    }

    @Override
    public List<Integer> getCollectionList(Integer userId) {
        String json = String.valueOf(redisUtil.get(COLLECTION_PRE + ":" + userId + ""));
        CollectionVo collectionVo = new CollectionVo();
        if (json != null) {
            collectionVo = new Gson().fromJson(json, CollectionVo.class);
        }
        return collectionVo.getProductList();
    }

    @Override
    public List<TbItemCollection> getTopCollectionItemList() {
        QueryWrapper<TbItemCollection> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("collection_count");
        List<TbItemCollection> list = tbItemCollectionMapper.selectList(queryWrapper).subList(0, 50);
        List<TbItemCollection> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int index = (int)(Math.random() * 50);
            result.add(list.get(index));
        }
        return result;
    }

    @Override
    public boolean importAll() {
        List<TbItem> list = tbItemMapper.selectList(new QueryWrapper<>());
        for (TbItem tbItem:list) {
            TbItemCollection tbItemCollection = new TbItemCollection();
            tbItemCollection.setProduct_id(tbItem.getId())
                    .setCollection_count(0)
                    .setCreated(tbItem.getCreated())
                    .setUpdated(tbItem.getCreated());
            tbItemCollectionMapper.insert(tbItemCollection);
        }
        return true;
    }
}
