package Udp;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Askclient {
    public static void main(String[] args) throws IOException {
        //创建socket发送和接收数据包
        DatagramSocket socket = new DatagramSocket(9999);
        Scanner sc = new Scanner(System.in);
        while (true){
            //发送数据包
            String str = sc.nextLine();//以回车作为标志
            byte[] buf = str.getBytes();
            InetAddress adress = InetAddress.getLocalHost();
            int port = 8888;  //与服务器端口一致
            DatagramPacket packet = new DatagramPacket(buf, buf.length, adress,port );
            socket.send(packet);
            if ("bye".equals(str)){
                break; //server说bye结束循环，socket.close();
            }

            //接收服务器的反馈
            byte[] buf2 = new byte[128];
            DatagramPacket fankui = new DatagramPacket(buf2, buf.length);
            socket.receive(fankui);
            System.out.println(new String(fankui.getData(),0, fankui.getLength()));
        }
        //关闭socket
        socket.close();

    }

}
