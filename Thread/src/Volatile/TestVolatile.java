package Volatile;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestVolatile.java
 * @Description volatile测试
 * @createTime 2021年01月20日 18:57:00
 *
 *
 * 两个线程间有共享的变量，比如这个flag,要想使得无论怎样一旦这个变量发生变化让其他变量看到（发现）
 * volatile synchronized final 修饰都是可见性的
 *
 */
public class TestVolatile {
    static  volatile boolean flag = true;
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag){
                    //System.out.println("===========");

                }
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = false;
    }
}
