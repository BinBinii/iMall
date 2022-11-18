package com.binbini.imall.service.comment;

import com.binbini.imall.dto.CommentDto;
import com.binbini.imall.pojo.TbComment;
import com.binbini.imall.vo.DataTablesResult;
import feign.hystrix.FallbackFactory;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/11/09:44
 * @Description:
 */
public class CommentClientFallbackFactory implements FallbackFactory {
    @Override
    public CommentClientService create(Throwable throwable) {
        return new CommentClientService() {
            @Override
            public int createComment(CommentDto commentDto) {
                return 0;
            }

            @Override
            public int createCommentMessage(Integer parentId, Integer userId, String content) {
                return 0;
            }

            @Override
            public DataTablesResult findCommentSearchPage(int start, int length, Integer productId, String orderByCol, String orderBySort) {
                return null;
            }

            @Override
            public TbComment findCommentById(Integer id) {
                return new TbComment()
                        .setId(id)
                        .setContent("id => " + id + " There is no corresponding information. The client provided degraded information. The service has been shut down.");
            }

            @Override
            public DataTablesResult findCommentByParentId(Integer start, Integer length, Integer parent_id) {
                return null;
            }

            @Override
            public boolean del(Integer id) {
                return false;
            }
        };
    }
}
