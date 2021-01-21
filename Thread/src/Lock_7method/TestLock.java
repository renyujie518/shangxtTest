package Lock_7method;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestLock.java
 * @Description TODO
 * @createTime 2021年01月20日 15:11:00
 */
public class TestLock {

    public static void main(String[] args) throws InterruptedException {
        //Lock
        /*
        * 1. 可重入锁  Lock ReadWriteLock synchronized
        * 是指在一个线程中可以多次获取同一把锁，比如：一个线程在执行一个带锁的方法，该方法中又调用了另一个需要相同锁的方法，
        * 则该线程可以直接执行调用的方法【即可重入】，而无需重新获得锁， 对于不同线程则相当于普通的互斥锁。这里注意会有Add，最大的作用是避免死锁。
        * 2.  ReadLock共享锁  WriteLock  ReentrantLock  synchronized独占锁（互斥）
        * 3，公平锁（先来后到），非公平锁（可以抢），NonfairSync()，，设置方法是在new ReentrantLock(true/false),默认false不公平
        * */
        Lock lock = new ReentrantLock();
        //创建锁1  拿不到锁就一直等待，拿到就执行后续代码
        lock.lock();

        //创建锁2，拿到返回true,带时间的是如果拿不到就尝试指定的时间，到时见还拿不到就放弃
        lock.tryLock();
        lock.tryLock(10, TimeUnit.MICROSECONDS);
        //创建锁3  拿不到锁就一直等待，中途可以被其他线程中断
        lock.lockInterruptibly();
        lock.unlock();
        lock.newCondition();//创建等待队列



/*
ReentrantReadWriteLock里面提供了很多丰富的方法，不过最主要的有两个方法：readLock()和writeLock()用来获取读锁和写锁。
* ReadWriteLock是一个接口。
ReentrantReadWriteLock是它的实现类，ReentrantReadWriteLock包括内部类ReadLock和WriteLock，这两个内部类实现了Lock接口。*/

        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        Lock readLock = rwLock.readLock();
        Lock readLock2 = rwLock.readLock();
        Lock writeLock = rwLock.writeLock();
        Lock writeLock2 = rwLock.writeLock();
        System.out.println(readLock==readLock2); //true  无参构造方法返回同一把读写锁
        System.out.println(writeLock==writeLock2);//true
    }

}
