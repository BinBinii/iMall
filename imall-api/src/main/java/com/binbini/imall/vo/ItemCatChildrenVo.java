package com.binbini.imall.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: BinBin
 * @Date: 2022/10/08/19:22
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ItemCatChildrenVo {
    private Integer value;

    private String label;
}
