package HomeWork2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName monimaipiao.java
 * @Description 模拟卖票
 * @createTime 2021年01月20日 21:28:00
 *
 * 启动3个线程打印递减数字，范围是：30~1。要求数字不能重复，
 * 每个线程打印一个数字后，立刻进入睡眠状态，睡眠时间300毫秒。（模拟多线程售票）
 *
 * 提示：定义一个类，其中定义一个数学类型的属性，用于记录要输出数字的上限（本题目中为30），
 * 定义一个方法，用于循环输出数字，循环条件为属性大于0。每次输出数字的时候让记录数字上限的属性自减1。
 * 注意，在循环输出的时候，使用try...finally代码块保证try中lock获取锁，finally中释放锁。
 */
public class monimaipiao {
    public static void main(String[] args) {
        Salse salse = new Salse(30);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    salse.sale1();
                }
            },"线程1").start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    salse.sale2();
                }
            },"线程2").start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    salse.sale3();
                }
            },"线程3").start();
    }
}

class  Salse{
    private int count;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();


    public Salse(int count) {
        this.count = count;
    }
    public void sale1()  {
        while (count>0){
            try {

                lock.lock();
                condition2.signalAll();
                condition3.signalAll();
                System.out.println(Thread.currentThread().getName()+":"+count);
                count--;
                try {
                    if (count>0){
                        condition1.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                lock.unlock();
            }

        }
    }
    public void sale2()  {
        while (count>0){
            try {
                lock.lock();
                condition1.signalAll();
                condition3.signalAll();
                System.out.println(Thread.currentThread().getName()+":"+count);
                count--;
                try {
                    if (count>0){
                        condition2.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                lock.unlock();
            }

        }
    }
    public void sale3()  {
        while (count>0){
            try {
                lock.lock();
                condition1.signalAll();
                condition2.signalAll();
                System.out.println(Thread.currentThread().getName()+":"+count);
                count--;
                try {
                    if (count>0){
                        condition3.await();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                lock.unlock();
            }

        }
    }
}
