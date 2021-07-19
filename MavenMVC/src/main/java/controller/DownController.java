package controller;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName DownController.java
 * @Description TODO
 * @createTime 2021年04月13日 00:46:00
 */
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class DownController {
    //声明单元方法：处理下载请求
    @RequestMapping("downFile")
    public void downFile(String filename, HttpServletResponse response, HttpServletRequest request) throws IOException {
        //设置下载资源的MIME类型?
        //设置响应头,告诉浏览器下载的资源需要存储到客户端的硬盘中，而不是解析打开。
        response.setHeader("Content-Disposition", "attachment;filename="+filename);
        //1.获取要下载的资源的流对象
        //获取文件的绝对路径
        String path=request.getServletContext().getRealPath("/img");
        //获取文件的二进制数据
        byte[] bytes = FileUtils.readFileToByteArray(new File(path, filename));
        //2.响应浏览器
        //获取输出流对象
        ServletOutputStream outputStream = response.getOutputStream();
        //响应资源
        outputStream.write(bytes);
    }



}
