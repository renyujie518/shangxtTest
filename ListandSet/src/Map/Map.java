package Map;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Map.java
 * @Description TODO
 * @createTime 2021年01月18日 21:33:00
 */
public interface Map {
    //定义方法
    public void put(Object key,Object value);
    public Object get(Object key);
    public int size();
    public boolean isEmpty();
    //定义内部接口
    interface Entry{
        public Object getKey();
        public Object getValue();
    }
}