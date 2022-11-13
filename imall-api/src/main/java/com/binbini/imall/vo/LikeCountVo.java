package com.binbini.imall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2022/11/12/11:47
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LikeCountVo implements Serializable {

    private Integer commentId;

    private Integer value;

}
