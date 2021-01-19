package LinkedList;
public class TestList {
    public static void main(String[] args) {
        //创建线性顺序表
        List list = new LinkedList();
        java.util.LinkedList<Object> objects = new java.util.LinkedList<>();
        //向末尾添加元素
        list.add("11111");
        list.add("aaaaa");
        list.add("bbbbb");
        list.add("33333");
        list.add("22222");
        list.add(3, "AAAAA");
        //进行各种操作验证添加
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        //System.out.println(list.toString());
        System.out.println(list.get(1));
    }
}