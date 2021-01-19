package Set;

import HashMap.HashMap;
import Map.Map;


/**
 * @author admin
 * @version 1.0.0
 * @ClassName HashSet.java
 * @Description TODO
 * @createTime 2021年01月19日 14:26:00
 */
public class HashSet implements Set {
    private HashMap map;
    private  static  final Object PRESENT = new Object();//定义的一个静态常量对象

    //构造函数
    public  HashSet(){
      map = new HashMap();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void add(Object obj) {
        map.put(obj,PRESENT);//添加时，传入的obj当做key,value部分每次传入的一样（value指向同一个object）

    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object obj) {
        //return map.get(obj) != null;   //get返回的是value,最好返回entry去判断
        return map.getEntry(obj) != null;
    }
}
