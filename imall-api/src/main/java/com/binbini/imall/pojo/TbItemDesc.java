package com.binbini.imall.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: BinBin
 * @Date: 2022/09/15/18:25
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbItemDesc implements Serializable {
    private Integer item_id;

    private String item_desc;

    private Date created;

    private Date updated;
}
