package com.binbini.imall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binbini.imall.config.RabbitmqConfig;
import com.binbini.imall.dto.ItemDto;
import com.binbini.imall.exception.IMallException;
import com.binbini.imall.mapper.TbItemCatMapper;
import com.binbini.imall.mapper.TbItemDescMapper;
import com.binbini.imall.mapper.TbItemMapper;
import com.binbini.imall.pojo.TbItem;
import com.binbini.imall.pojo.TbItemCat;
import com.binbini.imall.service.ItemService;
import com.binbini.imall.utils.IdGen;
import com.binbini.imall.utils.ObjectUtil;
import com.binbini.imall.vo.DataTablesResult;
import com.binbini.imall.vo.MessageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/09/15/18:57
 * @Description:
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public int createItem(ItemDto itemDto) {
        TbItem tbItem = new TbItem();
        if (itemDto.getTitle().equals("") || itemDto.getSell_point().equals("") ||
            itemDto.getPrice().equals("") || itemDto.getNum().equals("") || itemDto.getLimit_num().equals("") ||
            itemDto.getImage().equals("") || itemDto.getCid().equals("")) {
            return 0;
        }
        tbItem.setId(Integer.parseInt(IdGen.uuid()))
                .setTitle(itemDto.getTitle())
                .setSell_point(itemDto.getSell_point())
                .setVersion(itemDto.getVersion())
                .setColor(itemDto.getColor())
                .setPrice(itemDto.getPrice())
                .setNum(itemDto.getNum())
                .setLimit_num(itemDto.getLimit_num())
                .setImage(itemDto.getImage())
                .setCid(itemDto.getCid())
                .setCid_content(findItemCatCidContentById(itemDto.getCid()))
                .setStatus(1)
                .setCreated(new Date())
                .setUpdated(new Date());
        if (tbItemMapper.insert(tbItem) != 1) {
            return -1;
        }
        MessageVo<Integer> messageVo = new MessageVo<>();
        messageVo.setTitle("add_item");
        messageVo.setData(tbItem.getId());
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM, "inform.item", messageVo);
        return 1;
    }

    @Override
    public DataTablesResult findItemSearchPage(int start, int length, String search, String minDate, String maxDate) {
        DataTablesResult result = new DataTablesResult();

        PageHelper.startPage(start, length);
        QueryWrapper<TbItem> queryWrapper = new QueryWrapper<>();
        if (!search.equals("")) {
            queryWrapper.like("title", search).or().like("sell_point", "%" + search + "%");
        }
        if (maxDate != null && !maxDate.equals("") && minDate != null && !minDate.equals("")) {
            queryWrapper.between("created", minDate, maxDate);
        }
        queryWrapper.orderByDesc("id");
        List<TbItem> list = tbItemMapper.selectList(queryWrapper);

        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        result.setRecordsFiltered((int)pageInfo.getTotal());
        result.setRecordsTotal(getAllItemCount().getRecordsTotal());

        result.setSuccess(true);
        result.setData(list);
        return result;
    }

    @Override
    public TbItem findItemById(Integer id) {
        TbItem tbItem = tbItemMapper.selectById(id);
        if (tbItem == null) {
            throw new IMallException("No corresponding information");
        }
        return tbItem;
    }

    @Override
    public boolean update(ItemDto itemDto) {
        if (!ObjectUtil.checkObjAllFieldsIsNotNull(itemDto)) {
            return false;
        }
        TbItem tbItem = tbItemMapper.selectById(itemDto.getId());
        tbItem.setTitle(itemDto.getTitle())
                .setSell_point(itemDto.getSell_point())
                .setVersion(itemDto.getVersion())
                .setColor(itemDto.getColor())
                .setPrice(itemDto.getPrice())
                .setNum(itemDto.getNum())
                .setLimit_num(itemDto.getLimit_num())
                .setImage(itemDto.getImage())
                .setCid(itemDto.getCid())
                .setCid_content(findItemCatCidContentById(itemDto.getCid()))
                .setStatus(itemDto.getStatus())
                .setUpdated(new Date());
        if (tbItemMapper.updateById(tbItem) != 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean del(Integer id) {
//        boolean delTbItemResult = tbItemMapper.deleteById(id) == 1;
//        QueryWrapper<TbItemDesc> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("item_id", id);
//        boolean delTbItemDescResult = tbItemDescMapper.delete(queryWrapper) == 1;
//        return delTbItemResult && delTbItemDescResult;
        return tbItemMapper.deleteById(id) == 1;
    }

    private DataTablesResult getAllItemCount() {
        long count = tbItemMapper.selectCount(new QueryWrapper<>());
        DataTablesResult result = new DataTablesResult();
        result.setRecordsTotal((int) count);
        return result;
    }

    private String findItemCatCidContentById(Integer id) {
        TbItemCat tbItemCat = tbItemCatMapper.selectById(id);
        String originName = tbItemCat.getName();
        if (tbItemCat.getParent_id() != 0) {
            TbItemCat tbItemCatParent = tbItemCatMapper.selectById(tbItemCat.getParent_id());
            tbItemCat.setName(tbItemCatParent.getName() + " / " + originName);
        }
        return tbItemCat.getName();
    }
}
