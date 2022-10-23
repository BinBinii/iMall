package com.binbini.imall.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/10/08/19:18
 * @Description: Element UI 联级下拉列表 内容为: 商品类目 (最高为2级)
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ItemCatVo {

    private Integer value;

    private String label;

    private List<ItemCatChildrenVo> children;

}
