package Comcurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestContainer.java
 * @Description TODO
 * @createTime 2021年01月19日 16:52:00
 */
public class TestContainer {
    public static void main(String[] args) {
        //第一代  同步方法锁，效率低下
        Vector vector = new Vector();

        //第二代 ArrayList 线程不安全
        ArrayList arrayList = new ArrayList();
        //解决方案  同步代码块锁，效率提升有限
        List list = Collections.synchronizedList(arrayList);

        //第三代
        ConcurrentHashMap chm;
        CopyOnWriteArrayList cwal = new CopyOnWriteArrayList();
        cwal.add("111");
        CopyOnWriteArraySet cwas;





    }

}
