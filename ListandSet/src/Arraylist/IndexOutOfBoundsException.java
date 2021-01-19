package Arraylist;
//自定义异常
public class IndexOutOfBoundsException extends RuntimeException{
    public IndexOutOfBoundsException() {
        super();
    }

    public IndexOutOfBoundsException(String message) {
        super(message);
    }
}
