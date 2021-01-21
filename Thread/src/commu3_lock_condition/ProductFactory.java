package commu3_lock_condition;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
 *
 * 现在使用两个队列交替着唤醒，这样就不会出现生产者唤醒生产者的问题
 * Condition的同步队列（获取锁之后的队列）仍只有一个，但是可以有多个等待队列（争夺锁的队列）
 *
 *
 * Condition是在Java 1.5中才出现的，它用来替代传统的Object的wait()、notify()实现线程间的协作，
 * 相比使用Object的wait()、notify()，使用Condition1的await()、signal()这种方式实现线程间协作更加安全和高效。
 *
 * 调用Condition的await()、signal()、signalAll()方法，都必须在lock保护之内，就是说必须在lock.lock()和lock.unlock之间才可以使用
 */
public class ProductFactory {
    //储存商品
    List<String> list = new LinkedList<String>();//LinkedList在查询上快一点
    int max = 10;
    Lock lock = new ReentrantLock();//可重复锁
    //两个等待队列，可以交替的
    Condition produceCondition = lock.newCondition();
    Condition consumeCondition = lock.newCondition();




    public ProductFactory(int max) {
        this.max = max;
    }

    public ProductFactory() {

    }

    //生产一个商品，如果仓库已满，就等待，同时通知消费者消费
    public  void produce(String name){
        lock.lock();
        try{
            while (list.size() == max){
                try {
                    //this.wait();//为什么用this，因为synchronized监视的是当前对象this,这里this是工厂，也是另两个程序的实现对象factory
                    produceCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            list.add(name);
            System.out.println(Thread.currentThread().getName() + "生产了"+name+"商品，总数为：" + list.size());

           //只要生产，不管仓库是否满了（list.length()=max）.都可以精准的通知一个消费者消费
            consumeCondition.signal();
        }finally {
            lock.unlock();
        }
    }

    //消费一个商品，如果仓库为空，就等待，同时通知生产者生产
    public  void consume(){
        lock.lock();
       try {
           while (list.size()==0){
               try {
                   //this.wait();
                   consumeCondition.await();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }

           String removename = list.remove(0);
           System.out.println(Thread.currentThread().getName() + "消费了"+removename+"商品，总数为：" + list.size());

           if (list.size() == 0){
               //只要消费，仓库list就不满，就精准的唤醒一个生产者（具体唤醒哪个不确定）
               produceCondition.signal();
           }
       }finally {
           lock.unlock();
       }


    }

}
