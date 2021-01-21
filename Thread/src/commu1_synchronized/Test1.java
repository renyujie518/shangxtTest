package commu1_synchronized;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName commu1.Test.java
 * @Description
 * 功能：扩展生产者消费者问题。由一个生产者、一个消费者、一个商品扩展为多个生产者、多个消费者、多个商品。具体细节为
 *  *   最多10个商品，最少0个商品
 *  *   已经有10个商品，生产者不再生产，还要通知消费者消费
 *  *   没有商品了，消费者不再消费，还要通知生产者生产
 * @createTime 2021年01月19日 19:19:00
 */
public class Test1 {
    public static void main(String[] args) {
        ProductFactory factory = new ProductFactory();
        //创建10个生产者线程
        ProduceRunnable runnable1 = new ProduceRunnable(factory );//new 一个生产者工厂即可,通过构造方法传入同一个工厂
        for (int i = 0; i < 10; i++) {
            new Thread(runnable1,"生产者"+i).start();
        }

        //创建20个消费者线程
        ConsumeRunnable runnable2 = new ConsumeRunnable(factory );//一定注意，此处的线程队列中factory里既有生产者，也有消费者
        for (int i = 0; i < 20; i++) {
            new Thread(runnable2+"消费者"+i).start();
        }
    }
}
