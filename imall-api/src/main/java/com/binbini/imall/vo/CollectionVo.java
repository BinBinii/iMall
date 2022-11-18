package com.binbini.imall.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/14/11:18
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CollectionVo implements Serializable {

    private Integer user_id;

    private List<Integer> productList;

}
