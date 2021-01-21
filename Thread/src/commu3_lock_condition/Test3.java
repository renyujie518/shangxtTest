package commu3_lock_condition;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName commu1.Test.java
 * @Description
 * 功能：扩展生产者消费者问题。由一个生产者、一个消费者、一个商品扩展为多个生产者、多个消费者、多个商品。具体细节为
 *  *   最多10个商品，最少0个商品
 *  *   已经有10个商品，生产者不再生产，还要通知消费者消费
 *  *   没有商品了，消费者不再消费，还要通知生产者生产
 *
 *  使用匿名内部类访问所在方法的局部变量，比如此处的factory。这个变量必须是finnal的，1.8之后可省略
 * @createTime 2021年01月19日 19:19:00
 */
public class Test3 {
    public static void main(String[] args) {
      //创建和启动多个生产者和消费者线程


        int max = 30;
        ProductFactory factory = new ProductFactory(max);
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                //System.out.println(num);
                int i=0;
                while(true){
                    factory.produce("商品"+i);
                    i++;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        for (int i = 0; i <5 ; i++) {
            new Thread(runnable1,"生产者"+i).start();
        }
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                while(true){
                    factory.consume();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable2,"消费者"+i).start();
        }
    }
}




