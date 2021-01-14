package Socketbasic;

import entity.User;

import java.io.*;
import java.net.Socket;

public class LoginThread extends Thread {
    private Socket socket;

    public LoginThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        DataOutputStream dos = null;

        try {
            InputStream is = socket.getInputStream();
//        DataInputStream dis = new DataInputStream(is);
//        String result = dis.readUTF();
            ois = new ObjectInputStream(is);
            User result = (User) ois.readObject();
            System.out.println("来自客户端的请求： "+result);

            //给个客户端响应
            OutputStream os = socket.getOutputStream();
            dos = new DataOutputStream(os);
            if (result.getUserId().contains("admin")&& result.getPassWorld().length()>=6){
                dos.writeUTF("登录成功");
            }else {
                dos.writeUTF("登录失败");
            }

        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            //关闭资源  trycatch最好分开写，这样第一个关失败了，第二个也可以关 ，再加个判断
            //dis.close();
            try {
                if (ois != null){
                ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (dos != null){
                    dos.close();
                }
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
