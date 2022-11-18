package com.binbini.imall.demo;

/**
 * @Author: BinBin
 * @Date: 2022/11/14/17:40
 * @Description:
 */
public class Stack {
    class Element{
        public Object data ;
        public Element next ;
    }
    // 定义两个指针目的是分别指向栈底和栈顶
    private Element base ;
    private Element top ;

    // 初始化
    public void init(){
        Element element = new Element();
        base = element;
        top =  element;
        base.data = null;
    }

    // 入栈
    public void push(Object obj){
        Element element = new Element();
        element.data = obj;
        element.next = top;
        top = element;
    }

    // 出栈
    public Object pop(){
        if(base == top){
            return null;
        }
        Object obj = top.data;
        top = top.next;
        return obj;
    }

    // 取栈顶元素
    public Element getTop() {
        return top;
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        String str1 = "Num01",str2 = "Num02",str3 = "Num03",str4 = "Num04",str5 = "Num05";
        stack.init();
        stack.push(str1);stack.push(str2);stack.push(str3);stack.push(str4);stack.push(str5);
        stack.pop();stack.pop();
        System.out.println(stack.getTop().data);
    }
}
