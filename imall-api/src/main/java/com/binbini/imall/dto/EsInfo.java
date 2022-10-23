package com.binbini.imall.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2022/10/18/20:29
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class EsInfo implements Serializable {
    private String cluster_name;

    private String status;

    private String number_of_nodes;

    private Integer count;
}
