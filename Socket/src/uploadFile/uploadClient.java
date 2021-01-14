package uploadFile;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class uploadClient {
    public static void main(String[] args) throws IOException {
        //创建socket,指明服务器端口
        Socket socket = new Socket(InetAddress.getLocalHost(), 8800);
        BufferedInputStream bis =
                new BufferedInputStream(new FileInputStream("/Users/renyujie/Desktop/shangxtTest/Socket/src/uploadFile/a.txt"));
        BufferedOutputStream bos =
                new BufferedOutputStream(socket.getOutputStream());
        byte[] buf1 = new byte[1023];
        int len  = bis.read(buf1);//读原文件的一个字节给len
        while (len!=-1){
            bos.write(buf1,0,len);//写一个字节
            len = bis.read(buf1);//再读一个字节
        }
        bos.close();
        bis.close();

    }
}
