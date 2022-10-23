package com.binbini.imall.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2022/09/23/10:34
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CartProduct implements Serializable {

    private Integer item_id;

    private Integer num;

    private boolean checked;

}
