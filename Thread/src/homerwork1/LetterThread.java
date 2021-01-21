package homerwork1;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName LetterThread.java
 * @Description 字母线程
 * @createTime 2021年01月20日 20:21:00
 */
public class LetterThread extends Thread{
    private Printer printer;
    public LetterThread(Printer printer){
        super();
        this.printer = printer;

    }


    @Override
    public void run() {
        for (char i = 'A'; i <='Z' ; i++) {
            printer.print(i);
        }
    }
}
