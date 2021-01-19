package singleLinkedList;

import Arraylist.Iterator;

public class SingleLinkedList implements List {
    private Node head = new Node();//事先存在一个头结点，不存数据
    private int size;//节点数量，默认是0，不包括头结点

    @Override
    public int size() {
        return size;
    }

    @Override
    public Node get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("索引越界" + i);
        }
        //定义一个指针，指向头结点，循环，返回第i个node
        Node p = head;
        for (int j = 0; j <= i; j++) {
            p = p.next;  //一个node 里的next里存的就是下一个node的地址

        }
        return p;//注意，这是一个node
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object e) {
        return indexOf(e) >= 0;
    }

    @Override
    public int indexOf(Object e) {
        int index =-1;//不存在就返回-1，先默认不存在
        Node p = head;
        if (e == null){
            for (int i = 0; i < size; i++) {
                p = p.next; //下移一个
                if (e == p.data){  //null无法调用equal,空指针异常
                    index = i;
                    break;
                }
            }

        }else {
            for (int i = 0; i < size; i++) {
                p = p.next; //下移一个
                if (e.equals(p.data)){
                    index = i;
                    break;
                }
            }
        }

        return index;
    }

    @Override
    public void add(int i, Object e) {
        Node p = head;
        //先获取第i-1个元素,如果i= 0 ,就是指向头节点
        if (i > 0) {
            p = get(i - 1);
        }
        //新建节点，指定新节点的下一个节点。
        Node newNode = new Node(e);
        newNode.next = p.next;//因为p里储存的是上一次的连接地址，这里把历史存到新节点这里

        //指定新节点的前一个节点(前一个节点紧接着连接新节点)
        p.next = newNode;

        //节点数量+1
        size++;
    }

    @Override
    public void add(Object e) {//加到最后
        add(size, e);
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
        Node p = head;
        //先获取第i-1个元素,如果i= 0 ,就是指向头节点
        if (i > 0) {
            p = get(i - 1);
        }
        //在获取当前节点
        Node currentNode = p.next;

        //上一个节点的下一个节点是i+1节点
        p.next = p.next.next;
        //被删除的i节点没有下一个节点
        currentNode.next = null;
        size--;
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        Node p = head;
        for (int i = 0; i < size; i++) {
            p = p.next;//p指向第i个节点
            stringBuilder.append(p.data + ",");
        }
        if (size != 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
