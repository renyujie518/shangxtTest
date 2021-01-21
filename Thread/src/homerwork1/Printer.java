package homerwork1;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Printer.java
 * @Description 打印机
 * @createTime 2021年01月20日 20:23:00
 *
 * 12A34B.....5152z
 */
public class Printer {
    private int index = 1;
    public synchronized void print(int i){
        if (index%2 ==0){//如果不是1357就等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }//index是1，3，5，7开始打印数字
        System.out.print(i);
        System.out.print(i+1);
        index++;
        this.notify();
    }


    public synchronized void print(char ch){
        if (index%2 ==1){//如果不是2468就等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }//index是2,4,6,8开始打印字母
        System.out.print(ch);
        index++;
        this.notify();
    }
}
