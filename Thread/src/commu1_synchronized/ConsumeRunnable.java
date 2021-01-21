package commu1_synchronized;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ConsumeRunnable.java
 * @Description 消费者线程 线程要完成的任务
 * @createTime 2021年01月19日 19:21:00
 */
public class ConsumeRunnable implements Runnable {
    private ProductFactory factory;  //同一个工厂里去消费

    public ConsumeRunnable(ProductFactory factory) {
        this.factory = factory;
    }

    public ConsumeRunnable() {
    }

    public void setFactory(ProductFactory factory) {
        this.factory = factory;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            factory.consume();
        }

    }
}
