package com.binbini.imall.cashier;

import com.binbini.imall.vo.DataTablesResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: BinBin
 * @Date: 2022/10/17/18:18
 * @Description:
 */
public class Supermarket {

    private static List<Goods> goodsList = new ArrayList<>();
    private static List<GoodsItem> goodsItemList = new ArrayList<>();

    public static void main(String[] args) {
        Supermarket supermarket = new Supermarket();
        supermarket.initData();
        supermarket.index();
    }

    public void index() {
        System.out.println("--- 超市管理系统 ---");
        System.out.println("输入您要进行的操作：1 查看商品 2 输入购买数量 3 打印小票 4 退出");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();
        switch (choose) {
            case 1:
                System.out.println("--- 查看商品 ---");
                getAllGoodsItemIndex();
                break;
            case 2:
                System.out.println("--- 输入购买数量 ---");
                buyGoodsItemIndex();
                break;
            case 3:
                System.out.println("--- 打印小票 ---");
                print();
                break;
            case 4:
                System.out.println("--- 退出 ---");
                break;
            default:
                System.out.println("没有该选项");
                break;
        }
    }

    /**
     * 查看所有商品
     * @param start
     * @param length
     * @return
     */
    public DataTablesResult getAllItems(int start, int length) {
        DataTablesResult result = new DataTablesResult();
        result.setRecordsFiltered(goodsList.size());
        result.setRecordsTotal(goodsList.size());

        result.setSuccess(true);
        if (start == 1) {
            result.setData(goodsList.subList(0, length));
        } else {
            int endNum = start * length;
            if (endNum > goodsList.size()) {
                endNum = goodsList.size();
            }
            result.setData(goodsList.subList(start * length - length, endNum));
        }

        return result;
    }

    /**
     * 输入购买数量
     * @param id
     * @param number
     * @return
     */
    public boolean buyGoodsItem(int id, Integer number) {
        boolean result = false;
        for(Goods goods:goodsList) {
            if (goods.getId().equals(id)) {
                GoodsItem goodsItem = new GoodsItem();
                double countMoney = goods.getPrice() * number;
                goodsItem.setId(goods.getId())
                        .setName(goods.getName())
                        .setPrice(goods.getPrice())
                        .setUnit(goods.getUnit())
                        .setNumber(number)
                        .setMoney(countMoney);
                goodsItemList.add(goodsItem);
                result = true;
            }
        }
        return result;
    }

    /**
     * 查看商品界面
     * 1 上一页 2 下一页 3 返回
     */
    public void getAllGoodsItemIndex() {
        Scanner scanner = new Scanner(System.in);
        int start = 1;
        int length = 5;
        while(true) {
            DataTablesResult result = getAllItems(start, length);
            List<Goods> list = (List<Goods>) result.getData();
            int page;
            for(Goods goods:list) {
                System.out.println("商品ID:" + goods.getId() + " 商品名称:" + goods.getName() + " 商品价格:" + goods.getPrice() + "" + goods.getUnit());
            }
            page = (int)Math.ceil((double)result.getRecordsTotal() / (double)length);
            System.out.println(result.getRecordsTotal() + "个 第" + start + "页, 共" + page + "页");
            System.out.println("输入您要进行的操作：1 上一页 2 下一页 3 返回");
            int choose = scanner.nextInt();
            if (choose == 1) {
                if (start - 1 < 1) {
                    System.out.println("已经是第一页啦 :D");
                } else {
                    start--;
                }
            } if (choose == 2) {
                if (start + 1 > page) {
                    System.out.println("最后一页啦 :D");
                } else {
                    start++;
                }
            } if (choose == 3) {
                break;
            } if (choose != 1 && choose != 2 && choose != 3){
                System.out.println("--- 请输入正确的选择 ---");
            }
        }
        index();
    }

    /**
     * 购买商品界面
     * 输入商品ID 购买数量
     * -1退出录入
     */
    public void buyGoodsItemIndex() {
        Scanner scanner = new Scanner(System.in);
        int id, number;
        System.out.println("输入-1退出录入商品");
        while(true) {
            System.out.print("商品ID:");
            id = scanner.nextInt();
            if (id == -1) {
                break;
            }
            System.out.print("商品数量:");
            number = scanner.nextInt();
            if (number == -1) {
                break;
            }
            boolean flag = false;
            for (Goods goods:goodsList) {
                if (goods.getId().equals(id)) {
                    flag = true;
                    break;
                }
            }
            if (flag && number > 0 && number <= 100) {
                buyGoodsItem(id, number);
            } if (!flag){
                System.out.println("没有该商品");
            } if (number <= 0 || number > 100) {
                System.out.println("购买数量超出限额");
            }
            System.out.println("---------当前购物车(输入-1退出录入)--------");
            double countMoney = 0;
            for (GoodsItem goodsItem:goodsItemList) {
                System.out.println("商品ID:" + goodsItem.getId() + " 商品名称:" + goodsItem.getName() + " 商品价格:" + goodsItem.getPrice() + "" + goodsItem.getUnit() + " 该项商品合计:" + goodsItem.getMoney());
                countMoney += goodsItem.getMoney();
            }
            System.out.println("合计:" + countMoney);
            System.out.println("-------------------------");
        }
        index();
    }

    public void print() {
        System.out.println("--------购物清单--------");
        double countMoney = 0;
        for (GoodsItem goodsItem:goodsItemList) {
            countMoney += goodsItem.getMoney();
            System.out.println(goodsItem.getName()+"      "+goodsItem.getPrice()+""+goodsItem.getUnit()+"x"+goodsItem.getNumber());
        }
        System.out.println("合计: " + countMoney);
        System.out.println("-----------------------");
    }

    /**
     * 初始化数据
     */
    public void initData() {
        Goods goods1 = new Goods(1, "iPhone 14", 5999, "¥");
        Goods goods2 = new Goods(2, "iPhone 14 Pro", 7999, "¥");
        Goods goods3 = new Goods(3, "MacBook Air M2", 8299, "¥");
        Goods goods4 = new Goods(4, "MacBook Pro M2", 9999, "¥");
        Goods goods5 = new Goods(5, "NOMOS手表", 15999, "¥");
        Goods goods6 = new Goods(6, "Nikon Z6", 13999, "¥");
        goodsList.add(goods1);
        goodsList.add(goods2);
        goodsList.add(goods3);
        goodsList.add(goods4);
        goodsList.add(goods5);
        goodsList.add(goods6);
    }

}
