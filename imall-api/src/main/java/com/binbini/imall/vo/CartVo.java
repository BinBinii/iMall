package com.binbini.imall.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/23/10:23
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CartVo implements Serializable  {

    private Integer user_id;

    private List<CartProduct> productList;

}
