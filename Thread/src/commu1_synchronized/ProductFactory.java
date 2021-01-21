package commu1_synchronized;

import java.util.LinkedList;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ProductFactory.java
 * @Description TODO
 * @createTime 2021年01月19日 19:29:00
 * 关键字synchronized就具有使每个线程依次排队操作共享变量的功能。
 * 很显然，这种同步机制效率很低，但synchronized是其他并发容器实现的基础，
 * 任意一个对象都拥有自己的监视器，当这个对象由同步块或者这个对象的同步方法调用时，
 * 执行方法的线程必须先获取该对象的监视器才能进入同步块和同步方法，如果没有获取到监视器的线程将会被阻塞在同步块和同步方法的入口处，
 * 进入到BLOCKED状态
 ** 链接：https://www.jianshu.com/p/d53bf830fa09
 */
public class ProductFactory {
    //储存商品
    List<String> list = new LinkedList<String>();//LinkedList在查询上快一点
    int max = 10;


    //生产一个商品，如果仓库已满，就等待，同时通知消费者消费
    public synchronized void produce(String name){
        while (list.size() == max){
            try {
                this.wait();//为什么用this，因为synchronized监视的是当前对象this,这里this是工厂，也是另两个程序的实现对象factory
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
       list.add(name);
        System.out.println(Thread.currentThread().getName() + "生产了"+name+"商品，总数为：" + list.size());

        if (list.size() == max){
            //this.notify();//随机唤醒，有可能再次唤醒生产者，从而卡在while处
            this.notifyAll();//唤醒所有 ，肯定包括对方,肯定有被唤醒的线程
        }
    }

    //消费一个商品，如果仓库为空，就等待，同时通知生产者生产
    public  synchronized void consume(){
        while (list.size()==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String removename = list.remove(0);
        System.out.println(Thread.currentThread().getName() + "消费了"+removename+"商品，总数为：" + list.size());

        if (list.size() == 0){
            //this.notify();
            this.notifyAll();
        }

    }

}
