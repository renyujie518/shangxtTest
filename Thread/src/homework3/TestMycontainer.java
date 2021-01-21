package homework3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Mycontainer.java
 * @Description
 * @createTime 2021年01月21日 12:35:00
 *
 * 自定义容器，提供新增元素（add）和查看元素（get）方法。add方法向容器末尾位置新增元素，get方法通过参数传递的下标返回对应位置的元素。
 * 注意：get方法只读，不会删除对应位置的元素数据。
 * 要求为容器提供读写锁能力。即写写互斥、读写互斥，多线程并发访问当前容器，只能有一个线程做写操作，可以有多个线程同时执行读操作。
 * 读写锁解释：当有任意线程执行写操作，则其他所有线程阻塞等待；当有任意线程执行读操作，则其他所有写线程阻塞，读线程可执行。
 *
 *
 * 执行结果是在在add的时候都是轮着来的（线程0 10个add，线程1 10个add）,get的时候就没限制，（有可能线程1get紧接着线程2get,也可能线程1连着get好几次）
 */
public class TestMycontainer {
    public static void main(String[] args) throws InterruptedException {
        Mycontainer mycontainer = new Mycontainer();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {//多打印点数据，看的清楚
                        mycontainer.add(new Object());
                    }
                }
            },"addThread: "+i).start();
        }
        Thread.sleep(10);//睡一下，测试读
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        System.out.println(Thread.currentThread().getName()+"get:  "+mycontainer.get(0));
                    }
                }
            },"getThread: "+i).start();
        }
    }
}
class Mycontainer{
    List<Object> list = new ArrayList<>();
    final Object writeLock = new Object(); //同步代码块，锁定写锁
    boolean isWrite  = false;//针对读来说，只关心是否有线程正在执行写操作
    boolean isRead = false;

    public void add(Object obj)  {//写写互斥（同步代码块），写读互斥（自旋）
        //判断有没有 其他线程读，如果有，不应该再写，自旋等待
        while (isRead){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //执行到这说明没有其他线程读，考虑其他线程写
        synchronized (writeLock){
            try{
                //判断有没有其他线程写
                while (isWrite){
                    try {
                        writeLock.wait();//一旦有其他线程写，本线程进入等待队列
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //当前线程要开始写了
                isWrite = true;
                list.add(obj);
                System.out.println(Thread.currentThread().getName() + "add: " + obj);
                try {
                    Thread.sleep(300);//模拟写操作用时300ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                writeLock.notifyAll();//本线程呢写完了，可以唤醒其他写操作
            }finally {
                //可以使用finally,因为synchronized本身是原子操作
                isWrite = false;//add已经确定完毕了，最终形成闭环
            }
        }

    }

    public Object get(int index) {
        if (index>= list.size()){
            throw new IndexOutOfBoundsException();
        }
        while (isWrite){//有其他线程执行写操作，所以停住，这里采用自旋锁（空转一会，等待写操作完成）
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }//没有写操作，while的判断进不去，继续往下走
        try {
            isRead= true;//当前线程准备开始执行读操作
            Object result = list.get(index);
            try {
                Thread.sleep(300);//模拟读操作用时300ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        }finally {
            isRead = false;//return已经确定好了，最终形成闭环
        }

    }

}









