package uploadFile;

import java.io.*;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class uploadServer {
    public static void main(String[] args) throws IOException {
        //创建socket，用来发送和接收数据包
        ServerSocket serverSocket = new ServerSocket(8800);
        //serversocket监听
        Socket socket = serverSocket.accept();
        BufferedInputStream bis =
                new BufferedInputStream(socket.getInputStream());
        BufferedOutputStream bos =
                new BufferedOutputStream(new FileOutputStream("/Users/renyujie/Desktop/shangxtTest/Socket/src/uploadFile/b.txt"));
        byte[] buf1 = new byte[1023];
        int len  = bis.read(buf1);//读原文件的一个字节给len
        while (len!=-1){
            bos.write(buf1,0,len);//写一个字节
            len = bis.read(buf1);//再读一个字节
        }
        bos.close();
        bis.close();
        serverSocket.close();

    }
}
