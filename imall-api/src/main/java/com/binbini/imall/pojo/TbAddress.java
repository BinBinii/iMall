package com.binbini.imall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("tb_address")
public class TbAddress {
    @TableId(value = "address_id", type = IdType.AUTO)
    private Long address_id;        //自增id

    private Long user_id;           //用户id

    private String  user_name;      //用户名字

    private String  tel;            //电话

    private String  street_name;    //收货地址

    private Integer is_default;     //是否为默认收货

    private String label;           // 标签

}
