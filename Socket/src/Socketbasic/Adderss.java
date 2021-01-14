package Socketbasic;

import java.net.*;
import java.util.Arrays;

public class Adderss {
    public static void main(String[] args) throws UnknownHostException, MalformedURLException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("主机地址"+localHost.getHostAddress());
        System.out.println("主机名"+localHost.getHostName());
        System.out.println("主机名"+localHost.getHostName());
        System.out.println("========");

        InetAddress baidu = InetAddress.getByName("www.baidu.com");
        System.out.println(baidu);
        System.out.println(Arrays.toString(baidu.getAddress()));
        System.out.println("主机地址"+baidu.getHostAddress());
        System.out.println("主机名"+baidu.getHostName());
        System.out.println("主机名"+baidu.getHostName());
        System.out.println("========");

        //使用带封装端口号的方法.InetSocketAddress带public方法，所以可以直接new
        InetSocketAddress isa = new InetSocketAddress("www.baidu.com",222);
        System.out.println(isa.getAddress());
        System.out.println(isa.getPort());
        System.out.println("========");

        //URl
        URL url = new URL("https://www.bilibili.com/video/BV1gt4y1C7b1?p=303");
        System.out.println(url.getPort());
        System.out.println(url.getDefaultPort()); //443
        System.out.println(url.getProtocol());
        System.out.println(url.getPath());//video/BV1gt4y1C7b1
        System.out.println(url.getRef());


    }
}
