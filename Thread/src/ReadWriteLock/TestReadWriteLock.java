package ReadWriteLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestReadWriteLock.java
 * @Description 测试读写锁  多个读可以同时进行，写写，读写的时候会锁住（互斥）
 * @createTime 2021年01月20日 16:19:00
 */
public class TestReadWriteLock {
    public static void main(String[] args) {
      final Operator operator = new Operator();
        //创建5个读线程
        Runnable readrunnable = new Runnable() {
            @Override
            public void run() {
                //while (true){
                    operator.read();
                //}
            }
        };
        for (int i = 0; i < 5; i++) {
            new Thread(readrunnable,"读线程"+i).start();
        }

        //创建5个写线程
        Runnable writerunnable = new Runnable() {
            @Override
            public void run() {
              //  while (true){
                    operator.write();
               // }
            }
        };
        for (int i = 0; i < 5; i++) {
            new Thread(writerunnable ,"写线程"+i).start();
        }

    }
}

//内部类
class Operator{
    // private  Lock lock = new ReentrantLock();
    private ReadWriteLock rw1 = new ReentrantReadWriteLock();


    public void read(){
        //lock.lock();//获得锁就是独占的
        rw1.readLock().lock();//读操作并行
        try{
            System.out.println(Thread.currentThread().getName()+"读数据开始");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"读数据结束");
        }finally {
           // lock.unlock();
            rw1.readLock().unlock();
        }
    }

    public void write(){
        //lock.lock();
        rw1.writeLock().lock();//一定是写开始，写结束这种顺序，绝不会线程1开始写，紧接着线程2也开始写，，不会出现同时写的操作，写之间是互斥的
        try {
            System.out.println(Thread.currentThread().getName()+"写数据开始");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"写数据结束");
        }finally {
           // lock.unlock();
            rw1.writeLock().unlock();
        }
    }

}
