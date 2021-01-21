package BlockingQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ProductFactory.java
 * @Description TODO
 * @createTime 2021年01月19日 19:29:00
 * <p>
 * BlockingQueue即阻塞队列，从阻塞这个词可以看出，在某些情况下对阻塞队列的访问可能会造成阻塞。被阻塞的情况主要有如下两种：
 * 1. 当队列满了的时候进行入队列操作
 * 2. 当队列空了的时候进行出队列操作
 * 因此，当一个线程试图对一个已经满了的队列进行入队列操作时，它将会被阻塞，除非有另一个线程做了出队列操作；
 * 同样，当一个线程试图对一个空队列进行出队列操作时，它将会被阻塞，除非有另一个线程进行了入队操作。
 */
public class ProductFactory {
    //储存商品
    //BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(10);//10是上限
    BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>(10);
    int max = 15;
    Lock lock = new ReentrantLock();//可重复锁


    public ProductFactory(int max) {
        this.max = max;
        //blockingQueue = new ArrayBlockingQueue(max);
        blockingQueue = new LinkedBlockingQueue<String>(max);
    }

    public ProductFactory() {

    }

    //生产一个商品，如果仓库已满，就等待，同时通知消费者消费
    public void produce(String name) {
        try {
            blockingQueue.put(name);//添加到末尾（入队）
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "生产了" + name + "商品，总数为：" + blockingQueue.size());
    }

    //消费一个商品，如果仓库为空，就等待，同时通知生产者生产
    public void consume() {
        String removename = null;//去除第一个（出队）
        try {
            removename = blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "消费了" + removename + "商品，总数为：" + blockingQueue.size());
    }

}
