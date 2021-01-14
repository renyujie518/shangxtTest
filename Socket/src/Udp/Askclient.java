package Udp;

import java.io.IOException;
import java.net.*;

public class Askclient {
    public static void main(String[] args) throws IOException {
        //创建socket发送和接收数据包
        DatagramSocket socket = new DatagramSocket(9999);

        //发送数据包
        String str = "hello";
        byte[] buf = str.getBytes();
        InetAddress adress = InetAddress.getLocalHost();
        int port = 8888;  //与服务器端口一致
        DatagramPacket packet = new DatagramPacket(buf, buf.length, adress,port );
        socket.send(packet);

        //关闭socket
        socket.close();

    }

}
