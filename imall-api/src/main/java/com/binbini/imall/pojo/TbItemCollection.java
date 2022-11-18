package com.binbini.imall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: BinBin
 * @Date: 2022/11/13/15:48
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbItemCollection implements Serializable {
    @TableId(value = "product_id")
    private Integer product_id;

    private Integer collection_count;

    private Date created;

    private Date updated;
}
