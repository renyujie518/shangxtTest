package Socketbasic;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Loginserver {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //创建一个serversocket
        ServerSocket serverSocket = new ServerSocket(8800);

        //串行处理
/*        //监听   请求不到阻塞，请求到返回socket
        Socket socket = serverSocket.accept();

        //读取
        InputStream is = socket.getInputStream();
//        DataInputStream dis = new DataInputStream(is);
//        String result = dis.readUTF();
        ObjectInputStream ois = new ObjectInputStream(is);
        User result = (User) ois.readObject();
        System.out.println("来自客户端的请求： "+result);

        //给个客户端响应
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        if (result.getUserId().contains("admin")&& result.getPassWorld().length()>=6){
            dos.writeUTF("登录成功");
        }else {
            dos.writeUTF("登录失败");
        }


        //关闭资源
        //dis.close();
        ois.close();
        dos.close();
        //is.close();
        //socket.close();
        serverSocket.close();*/

        //多协程处理
        int number =1;
        while (true){
            Socket socket = serverSocket.accept();
            //为每个请求创建线程
            new LoginThread(socket).start();
            //统计下客户端的Ip地址和总的请求次数
            InetAddress inetAddress = socket.getInetAddress();
            System.out.println("这是第"+(number++)+"个请求");
            System.out.println("对方的IP地址是"+inetAddress.getHostAddress());
        }
    }
}
