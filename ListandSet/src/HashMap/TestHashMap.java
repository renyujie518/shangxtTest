package HashMap;


import Map.Map;

import java.util.Arrays;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestHashMap.java
 * @Description TODO
 * @createTime 2021年01月18日 20:54:00
 */
public class TestHashMap {
    public static void main(String[] args) {
        java.util.HashMap  map2;
        HashMap map = new HashMap();
        map.put(23,"China");
        map.put(36,"Japan");
        map.put(48,"America");
        map.put(86,"the United States");
        map.put(67,"France");
        map.put(23,"Italian");
        map.put(47,"England");
        System.out.println(map.size());
        System.out.println(Arrays.toString(map.table));
        System.out.println(map.get(23));
        System.out.println(map.get(40));//没有就null
        System.out.println(map);
    }
}