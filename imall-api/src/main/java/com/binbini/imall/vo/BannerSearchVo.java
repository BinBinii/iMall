package com.binbini.imall.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/30/10:35
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BannerSearchVo implements Serializable {
    private Integer banner_search_id;           // banner search id

    private String title;                       // 标题

    private Integer sort;                       // 排序

    private List<BannerSearchItemVo> itemList;  // 商品列表
}
