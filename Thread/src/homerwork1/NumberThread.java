package homerwork1;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName NumberThread.java
 * @Description 打印数字的线程
 * @createTime 2021年01月20日 20:21:00
 */
public class NumberThread extends Thread{
    private Printer printer;

    public NumberThread(Printer printer){
        super();
        this.printer = printer;
    }
    @Override
    public void run() {
        for (int i = 1; i <= 52; i+=2) {//i= 1,3,5,7....
            printer.print(i);
        }

    }
}
