package controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import pojo.UploadResult;
import pojo.User;
import service.RegService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName RegController.java
 * @Description TODO
 * @createTime 2021年04月12日 22:37:00
 */
@Controller
public class RegController {
    //声明业务层属性
    @Autowired
    private RegService regService;
    //声明单元方法:完成用户注册
    @RequestMapping("userReg")
    public String userReg(User user){
        //处理请求
        int i = regService.insUserInfoService(user);
        //响应结果
        return "redirect:/login.jsp";
    }



    //声明单元方法 处理文件上传请求
    //形参 MultipartFile
    //该形参是用来接收 DispatcherServlet解析 equest对象后存储了文件二进制数据的对象。
    //形参的名字必须是上传请求中的文件的键名 formData.append("photo",file);
    @RequestMapping("regUpload")
    public void regUpload(MultipartFile photo, HttpServletResponse response, HttpServletRequest request) throws IOException {
        //确定文件存储路径，走配置文件会很方便修改 然后也可以存在自己的项目中
        String path = request.getServletContext().getRealPath("/img");
        //获取文件的后缀名
        String oldname = photo.getOriginalFilename();
        //确定存储名字
        String newName = UUID.randomUUID() + oldname.substring(oldname.lastIndexOf("."));  //.jpg
        //完成存储
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
        photo.transferTo(new File(file,newName));
        //响应结果是一个json对象
        UploadResult uploadResult = new UploadResult(true,null,newName);
        String jsonStr = new Gson().toJson(uploadResult);
        //直接响应
        response.getWriter().write(jsonStr);

    }
}
