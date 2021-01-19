package HashMap;

import Map.Map;

import java.util.Arrays;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName HashMap.java
 * @Description TODO
 * @createTime 2021年01月18日 21:36:00
 */
public class HashMap implements Map {
    transient Entry[] table = null;  //子接口，table用来指向主数组，相当于数组的名字，并不是真正的储存空间
    transient  int size= 0;//链表的节点数

    //实现类的构造函数
    public HashMap() {
        this(16);  //默认大小16
    }

    public HashMap(int capacity) {//1.7要求必须是2的整次幂
        table = new Entry[capacity];
    }

    //接口中的内部接口的实现类
    class Entry implements Map.Entry{  //Entry是哈希表中的链表的节点类
        final Object key;  //不可改变
        Object value;
        Entry next;  //指向下一个节点
        int hash;  //key的哈希吗

        public Entry(int hash,Object key,Object value,Entry next) {  //构造函数，用于new

            this.key = key;
            this.value = value;
            this.next = next;
            this.hash = hash;
        }

        @Override
        public Object getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public String toString() {
            if (next!=null){
                return "Entry{" +
                        "key=" + key +
                        ", value=" + value +
                        ", next=" + next +
                        ", hash=" + hash +
                        '}';
            }else {
                return "Entry{" +
                        "key=" + key +
                        ", value=" + value +  //next就不要了，防止空指针异常
                        ", hash=" + hash +
                        '}';
            }

        }
    }

    @Override
    public void put(Object key, Object value) {
        //1,计算key的哈希码
        int hash = key.hashCode();
        //2.根据哈希码计算存储的位置（主数组的索引）
        int index = hash % table.length;
        //3. 存到指定位置
        if(table[index] == null){//11111该插入位置了，结果发现这个位置为空（一般为开始的时候），直接插入即可
            table[index] = new Entry(hash,key,value,null);
            size++;
        }else {
            //指定的位置不为空，发生了冲突，需要逐个比较
            Entry entry = table[index]; //指向链表的第一个节点
            while (entry!=null){
                //进行key内容比较，如果找到相同的key，22222使用新的value覆盖旧的value
                if (entry.hash ==hash && (entry.key == key || entry.getKey().equals(key))){
                    //如果entry里的key和put传入的想放的key一致,算出的hash值和之前存的hash值一致
                    //找到相同的key,用新的value覆盖旧的value
                    entry.value = value;
                    //返回(找到后就不执行,结束方法的执行)
                    return;
                }
            }
            //33333发生冲突，且没有相同的key,添加一个新的节点做链表的首节点(相当于插队到最前面)
            Entry firstentry = table[index];
            table[index] = new Entry(hash,key,value,firstentry);
            size++;
        }
    }

    @Override
    public Object get(Object key) {
        //寻找对应的Entry
        Entry entry = getEntry(key);

        //4.返回Entry中的value
        return entry == null ? null : entry.getValue();//如果entry是null,就return null，否则返回getValue的值
    }

    public Entry getEntry(Object key) { //这是为了后续写Hast的时候方便调用，判断返回的entry是否为空
        //1,计算key的哈希码
        int hash = key.hashCode();
        //2.根据哈希码计算存储的位置（主数组的索引）
        int index = hash % table.length;
        Entry entry = null;
        if(table[index]!=null){  //111 如果table数组中是空的位置肯定没有，否则的话由于上一行已经赋初值为null了，就直接返回空
            Entry currententry = table[index]; //指向链表的第一个节点(开始横着走)
            while (currententry!=null){
                if (currententry.hash==hash && (currententry.key == key || currententry.getKey().equals(key))){
                    //2222 发生了冲突，也找到了参数传入进来的相同的key,那就把这个临时entry返回
                    entry = currententry;
                    break;
                }
                currententry = currententry.next;//先判断再指向下一个
            }
            //循环完毕也没break,那就相当于没找到，那就直接到步骤4，entry是null
        }
        return entry;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        for (int i = 0; i < table.length; i++) {
            if (table[i]!=null){
                Entry entry = table[i];//横着走，指向索引为i的第一个节点
                do {
                    stringBuilder.append(entry.key + "=" + entry.value+",");
                    entry = entry.next;
                }while (entry!=null);  //满足while条件执行do
            }
        }
        if (size>0){
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        stringBuilder.append("}");
        return stringBuilder.toString();


    }
}
