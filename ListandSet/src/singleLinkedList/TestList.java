package singleLinkedList;

public class TestList {
    public static void main(String[] args) {
        //创建线性顺序表
        List list = new SingleLinkedList();
        //向末尾添加元素
        list.add("11111");
        list.add("aaaaa");
        list.add("bbbbb");
        list.add("33333");
        list.add("22222");
        System.out.println("add前： "+list.toString());
        list.add(3, "AAAAA");
        System.out.println("add前： "+list.toString());

        System.out.println("remove前： "+list.toString());
        list.remove(2);
        System.out.println("remove后： "+list.toString());
        //进行各种操作验证添加
        System.out.println(list.size());
        System.out.println(list.get(0));
        System.out.println("empty?"+list.isEmpty());
        System.out.println("comtains?"+list.contains("44444"));
        System.out.println(list.indexOf("22222"));
        System.out.println(list.toString());
    }
}