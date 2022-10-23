package com.binbini.imall.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/05/10:07
 * @Description: 分页实体类
 */
@Data
public class DataTablesResult implements Serializable {

    private Boolean success;

    private int recordsTotal;

    private int recordsFiltered;

    private String error;

    private List<?> data;

}
