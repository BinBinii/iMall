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
 * @Date: 2022/09/30/09:31
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TbBannerSearch implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;                         // 自增ID

    private String title;                       // 标题

    private Integer sort;                       // 排序

    private Integer status;                     // 状态码

    private String remark;                      // 备注

    private Date created;                       // 备注时间

    private Date updated;                       // 更新时间

}
