package LinkedList;

import Arraylist.Iterator;
/**
 *
 * @ClassName LinkedList
 * @Description TODO
 * @author renyujie
 * @version 1.0.0
 * @date 2021/1/18
 **/
public class LinkedList implements List{
    transient int size = 0;
    transient Node first;//储存指针，指向链表的第一个节点
    transient Node last;//储存指针，指向链表的最后一个节点

    /*
    内部类，表示LinkedList的节点
    * */
    private static class Node<E> {

        E item;
        Node<E> next;  //前后存放的都是node的类型的地址
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {   //内部类的构造函数，new Node<>  时候用
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    ", next=" + next +
                    ", prev=" + prev +
                    '}';
        }
    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public Object get(int i) {
        return node(i).item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object e) {
        return false;
    }

    @Override
    public int indexOf(Object e) {
        return 0;
    }

    @Override
    public void add(int index, Object element) {
        if (index == size)  //如果索引恰好等于size,就相当于linkLast，在最后加
            linkLast(element);
        else
            linkBefore(element, node(index));

    }
    void linkBefore(Object e, Node succ) {  //Inserts element e before non-null Node succ.（succ可以看做是被挤的那个）
        // assert succ != null;
        final Node pred = succ.prev;   //把需要插入位置的前一个node的前一个地址拿出来
        final Node newNode = new Node<>(pred, e, succ);//根据传入内容建立新的node，前放老前一个,后方被挤的
        succ.prev = newNode;//被挤的放在插入的后面
        if (pred == null)
            first = newNode; //相当于索引为0.插到首地址
        else
            pred.next = newNode; //将新data与上一个node（被挤的之前连接的上一个）连接上
        size++;

    }

    //查询指定索引的节点
    Node node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) { //前一半节点从头开始找
            Node x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {          //后一半节点从尾开始找
            Node x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }



    @Override
    public void add(Object e) {
        linkLast(e);
    }

    void linkLast(Object e) {   //Links e as last element.
        final Node l = last;  //last是一个node  指向链表的最后一个节点，这里取出目前这个链表的最后一个节点为l
        final Node newNode = new Node<>(l, e, null);  //连接到l后面
        last = newNode; //newNode替代成为最后一个节点,，注意，这里形成闭环
        if (l == null)
            first = newNode;   //初始的时候，l为空，所以放到first
        else
            l.next = newNode; //将新节点连接到l(l这里看做是原来的指向的那个最后的节点),后面新节点的next又是null
        size++;

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
        return null;
    }
}
