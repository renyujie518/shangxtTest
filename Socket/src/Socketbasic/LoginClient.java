package Socketbasic;

import entity.User;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class LoginClient {
    public static void main(String[] args) throws IOException {
        //创建socket,指明服务器端口
        Socket socket = new Socket(InetAddress.getLocalHost(), 8800);

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名： ");
        String userId = sc.next();
        System.out.println("请输入密码： ");
        String passWorld = sc.next();
        User user = new User(userId, passWorld);


        //发送
        OutputStream outputStream = socket.getOutputStream();
        //DataOutputStream dos = new DataOutputStream(outputStream);
        //dos.writeUTF("username=admin&password=123456");
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        oos.writeObject(user);



        //接收服务器消息
        InputStream inputStream = socket.getInputStream();
        DataInputStream dis = new DataInputStream(inputStream);
        String result = dis.readUTF();
        System.out.println("来自服务器的响应： "+result);

        //关闭资源
        dis.close();//先关读入，读完了再关输出
        oos.close();
        //dos.close();
        //outputStream.close();
        //socket.close();


    }
}
