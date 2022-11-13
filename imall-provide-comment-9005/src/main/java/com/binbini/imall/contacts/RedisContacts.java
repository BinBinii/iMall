package com.binbini.imall.contacts;

/**
 * @Author: BinBin
 * @Date: 2022/11/12/09:55
 * @Description:
 */
public class RedisContacts {

    /**
     * 用户点赞数据的key
     */
    public static final String MAP_KEY_USER_LIKED = "MAP_USER_LIKED";

    /**
     * 评论被点赞的key
     */
    public static final String MAP_KEY_COMMENT_LIKED_COUNT = "MAP_COMMENT_LIKED_COUNT";

    /**
     * 拼接被点赞的用户id和点赞的人的id作为key
     * @param likedCommentId   被点赞评论ID
     * @param likedPostId      点赞人ID
     * @return
     */
    public static String getLikedKey(Integer likedCommentId, Integer likedPostId){
        return likedCommentId +
                "::" +
                likedPostId;
    }
}
