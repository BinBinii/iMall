package com.binbini.imall.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: BinBin
 * @Date: 2022/10/29/14:59
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class MessageVo<T> implements Serializable {

    private String title;

    private T data;

}
