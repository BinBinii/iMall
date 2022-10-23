package com.binbini.imall.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2022/10/18/20:32
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SearchItem implements Serializable {
    private Integer product_id;

    private String product_title;

    private double price;

    private String image_big;

    private Integer cid;
}
