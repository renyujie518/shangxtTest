package Arraylist;

import java.util.Arrays;

public class ArrayList implements List {
    private transient Object[] elementdata;//Arraylist底层是个动态增长的数组.elementdata是数组的引用
    private int size;  //集合中元素的个数，不是数组空间的容量length,默认为0,增加删除元素时size变化

    public ArrayList() {
        this(10);//1.7初始容量是10
    }

    public ArrayList(int initialCapacity){
        elementdata = new Object[initialCapacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object get(int i) {
        if(i>=size || i<0){
            throw new IndexOutOfBoundsException("数组索引越界" + i);
        }
        return elementdata[i];
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object e) {
        return indexOf(e)>=0;//能找到索引就代表有
    }

    @Override
    public int indexOf(Object e) {
        int index =-1;//不存在就返回-1，先默认不存在
        if (e == null){
            for (int i = 0; i < size; i++) {
                if (e == elementdata[i]){  //null无法调用equal,空指针异常
                    index = i;
                    break;
                }
            }

        }else {
            for (int i = 0; i < size; i++) {
                if (e.equals(elementdata[i])){
                    index = i;
                    break;
                }
            }
        }

        return index;
    }

    @Override
    public void add(int index, Object e) {
        //如果数组数组已满，自动扩容
        if(elementdata.length==size){
            grow();
        }
        //后移元素，留出空位，再把元素添加到第i个位置,注意，这里是从后往前移
        for (int i = size;i>index;i--){
            elementdata[i] = elementdata[i - 1];
        }
        //把e添加过来
        elementdata[index] = e;
        size++;

    }

    @Override
    public void add(Object e) {//添加到最后
        //如果数组数组已满，自动扩容
        if(elementdata.length==size){
            grow();
        }
        elementdata[size] = e;
        size++;

    }

    private void grow() {//扩容
        //创建一个更大的数组，把原先的数据拷贝过去，再把elementdata指向这个新数组
        int oldCapacity = elementdata.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);//扩容1.5倍
        Object[] newArray = new Object[newCapacity];
        for (int i = 0; i < elementdata.length; i++) {
            newArray[i] = elementdata[i];
        }
        elementdata = newArray;
        //或者直接用已有的函数替代上述步骤
        //elementdata = Arrays.copyOf(elementdata, elementdata.length + (elementdata.length) >> 1);
    }

    @Override
    public boolean addBefore(Object obj, Object e) {
        return false;
    }

    @Override
    public boolean addAfter(Object obj, Object e) {
        return false;
    }

    @Override
    public Object remove(int i) {
        return null;
    }

    @Override
    public boolean remove(Object e) {
        return false;
    }

    @Override
    public Object replace(int i, Object e) {
        return null;
    }

    @Override
    public Iterator iterator() {
        return new Itr();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            builder.append(elementdata[i] + ",");
        }

        if (size>0){
            builder.deleteCharAt(builder.length()-1);

        }
        builder.append("]");
        //builder.append("size: "+size);
        return builder.toString();
    }

    private class Itr<T> implements Iterator<T> {
        int cursor =0;
        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            if (cursor >=size){
                throw new RuntimeException("已经没有元素遍历");
            }
            return (T) elementdata[cursor++];

        }
    }
}
