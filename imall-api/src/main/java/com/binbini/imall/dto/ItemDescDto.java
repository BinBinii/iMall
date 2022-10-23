package com.binbini.imall.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2022/09/16/09:26
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ItemDescDto implements Serializable {
    private Integer item_id;

    private String item_desc;
}
