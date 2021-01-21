package homerwork1;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Test.java
 * @Description 作业一
 * @createTime 2021年01月20日 20:19:00
 *
 * 12A34B.....5152z
 */
public class Test {
    public static void main(String[] args) {
        Printer printer = new Printer();
        //创建两个线程对象
        NumberThread numberThread = new NumberThread(printer);
        LetterThread letterThread = new LetterThread(printer);

        //启动线程
        numberThread.start();
        letterThread.start();


    }
}
