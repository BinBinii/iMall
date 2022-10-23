package com.binbini.imall.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2022/09/30/10:45
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BannerSearchItemVo implements Serializable {
    private Integer id;

    private Integer item_id;

    private String title;

    private String image;
}
