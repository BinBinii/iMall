package com.binbini.imall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binbini.imall.mapper.TbOrderItemMapper;
import com.binbini.imall.mapper.TbOrderMapper;
import com.binbini.imall.pojo.TbOrder;
import com.binbini.imall.pojo.TbOrderItem;
import com.binbini.imall.service.OrderItemService;
import com.binbini.imall.vo.OrderItemListVo;
import com.binbini.imall.vo.OrderItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/11/19/15:19
 * @Description:
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;

    @Override
    public List<OrderItemListVo> findOrderItemByUserId(Integer userId, Integer type) {
        if (userId.equals("") || type.equals("")) {
            return null;
        }
        QueryWrapper<TbOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        if (type.equals(0)) {
            queryWrapper.eq("status", 1).or().eq("status", 2).or().eq("status", 3).or().eq("status", 4);
        } else if (type.equals(1)) {
            queryWrapper.eq("status", 0);
        } else if (type.equals(2)) {
            queryWrapper.eq("status", 1).or().eq("status", 2).or().eq("status", 3);
        } else if (type.equals(3)) {
            queryWrapper.eq("status", 5).or().eq("status", 6);
        }
        List<TbOrder> list = tbOrderMapper.selectList(queryWrapper);
        List<OrderItemListVo> result = new ArrayList<>();
        for (TbOrder tbOrder:list) {
            OrderItemListVo orderItemListVo = new OrderItemListVo();
            orderItemListVo.setOrderId(tbOrder.getId());
            QueryWrapper<TbOrderItem> tbOrderItemQueryWrapper = new QueryWrapper<>();
            tbOrderItemQueryWrapper.eq("order_number", tbOrder.getOrder_number());
            List<TbOrderItem> tbOrderItems = tbOrderItemMapper.selectList(tbOrderItemQueryWrapper);
            orderItemListVo.setStatus(tbOrder.getStatus())
                    .setData(tbOrderItems);
            result.add(orderItemListVo);
        }
        return result;
    }

    @Override
    public List<OrderItemVo> findOrderItemByOrderId(Integer orderId) {
        if (orderId.equals("")) {
            return null;
        }
        TbOrder tbOrder = tbOrderMapper.selectById(orderId);
        List<OrderItemVo> result = new ArrayList<>();
        return result;
    }
}
