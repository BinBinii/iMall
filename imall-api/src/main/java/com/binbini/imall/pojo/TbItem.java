package com.binbini.imall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: BinBin
 * @Date: 2022/09/15/16:38
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Document(indexName = "item", shards = 1, replicas = 0)
public class TbItem implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;                         // 自增ID

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String title;                       // 商品标题

    private String sell_point;                  // 商品卖点

    private String version;                     // 版本

    private String color;                       // 颜色

    private String price;                       // 商品价格

    private Integer num;                        // 库存数量

    private Integer limit_num;                  // 售卖数量限制

    private String image;                       // 商品图片

    private Integer cid;                        // 所属分类

    private String cid_content;                 // 所属分类

    private Integer status;                     // 商品状态 1正常 0下架

    private Date created;                       // 创建时间

    private Date updated;                       // 更新时间

}
