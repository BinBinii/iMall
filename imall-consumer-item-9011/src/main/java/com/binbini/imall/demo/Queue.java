package com.binbini.imall.demo;

/**
 * @Author: BinBin
 * @Date: 2022/11/14/17:55
 * @Description:
 */
public class Queue {
    public static void main(String[] args) {
        CyclicArrayQueue arrayQueue = new CyclicArrayQueue<>(3);
        System.out.println("入队：" + arrayQueue.add("Java"));
        System.out.println("入队：" + arrayQueue.add("C"));
        System.out.println("入队：" + arrayQueue.add("Python"));
        System.out.println(arrayQueue);
        System.out.println("当前队列头元素："+arrayQueue.peek());

        System.out.println("出队：" + arrayQueue.take());
        System.out.println(arrayQueue);
    }
    static class CyclicArrayQueue<E> {
        private int front;
        private int rear;
        private E[] data;
        private int maxSize;

        public CyclicArrayQueue(int capacity) {
            this.front = 0;
            this.rear = 0;
            // 预留一个判断是否队满。
            this.maxSize = capacity + 1;
            this.data = (E[]) new Object[maxSize];
        }

        public boolean isFull() {
            return (rear + 1) % maxSize == front;
        }

        public boolean isEmpty() {
            return rear == front;
        }

        public int size() {
            return (rear - front + maxSize) % maxSize;
        }

        public boolean add(E element) {
            if (element == null) {
                return false;
            }
            if (isFull()) {
                return false;
            }
            data[rear] = element;
            rear = (rear + 1) % maxSize;
            return true;
        }

        public E peek() {
            if (isEmpty()) {
                return null;
            }
            return data[front];
        }

        public E take() {
            if (isEmpty()) {
                return null;
            }
            E takeValue = data[front];
            front = (front + 1) % maxSize;
            return takeValue;
        }

        @Override
        public String toString() {
            StringBuffer buffer = new StringBuffer();
            if (isEmpty()) {
                return buffer.append("[]").toString();
            }

            buffer.append("[");
            for (int i = front; i < front + size(); i++) {
                buffer.append(data[i % maxSize]).append(",");
            }
            // 删除最后一个逗号
            buffer.deleteCharAt(buffer.length() - 1);
            buffer.append("]");

            return buffer.toString();
        }
    }
}
