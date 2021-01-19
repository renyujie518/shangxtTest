package Set;

import java.util.HashSet;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestHashset.java
 * @Description TODO
 * @createTime 2021年01月19日 14:21:00
 */
public class TestHashset {
    public static void main(String[] args) {
        HashSet set = new HashSet();
        set.add("JavaSE");
        set.add("Mysql");
        set.add("JavaScript");
        set.add("jQuery");
        set.add("JavaSE");
        System.out.println(set.size());
        System.out.println(set.contains("JavaSE"));
        System.out.println(set.contains("JavaSE2"));
        System.out.println(set);
    }
}
