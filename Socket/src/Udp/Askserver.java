package Udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Askserver {
    public static void main(String[] args) throws IOException {
        //创建socket，用来发送和接收数据包
        DatagramSocket socket = new DatagramSocket(8888);

        //接收数据
        byte[] buf = new byte[128];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        //System.out.println(new String(packet.getData()));//这样会把128剩余的也打印成空白，这是不太好的
        System.out.println(new String(packet.getData(), 0, packet.getLength()));
        System.out.println(packet.getLength());
        System.out.println(packet.getPort());//9999 对方接收数据端口
        System.out.println(packet.getAddress());//对方的ip地址

        //关闭socket
        socket.close();


    }
}
