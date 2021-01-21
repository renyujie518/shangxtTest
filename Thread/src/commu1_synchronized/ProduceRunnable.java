package commu1_synchronized;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ProduceRunnable.java
 * @Description 生产者线程
 * @createTime 2021年01月19日 19:20:00
 */
public class ProduceRunnable implements Runnable{
    private ProductFactory factory;  //同一个工厂里去生产

    public ProduceRunnable(ProductFactory factory) {
        this.factory = factory;
    }

    public ProduceRunnable() {
    }

    public void setFactory(ProductFactory factory) {
        this.factory = factory;
    }

    @Override
    public void run() {
        int i= 0;
        while (true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            factory.produce("生产商品"+i);
            i++;
        }

    }
}
