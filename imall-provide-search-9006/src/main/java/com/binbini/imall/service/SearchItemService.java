package com.binbini.imall.service;

import com.binbini.imall.dto.EsInfo;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author: BinBin
 * @Date: 2022/10/18/21:58
 * @Description:
 */
@Service
public interface SearchItemService {
    /**
     * 同步索引
     * @return
     */
    boolean importAllItems() throws IOException;

    /**
     * 删除索引
     * @return
     */
    boolean deleteAllItems() throws IOException;

    /**
     * 获取ES基本信息
     * @return
     */
    EsInfo getEsInfo();
}
