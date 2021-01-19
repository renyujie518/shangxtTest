package Arraylist;

public class ArrayTest {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        java.util.ArrayList<Object> list2 = new java.util.ArrayList<>();
        list.add("1111");
        list.add("aaaa");
        list.add("hhhh");
        list.add("2222");
        list.add("2222");
        list.add("2222");
        list.add("2222");
        list.add("2222");
        list.add("2222");
        list.add("2222");
        list.add("2222");
        list.add(null);
        list.add(3, "AAAAa");
        //验证
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        System.out.println(list.get(1));
        System.out.println(list.contains("4444"));
        System.out.println(list.indexOf("2222"));
        System.out.println(list.indexOf(null));
        System.out.println(list.toString());
        System.out.println("========");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            System.out.println(next);
        }
    }
}


