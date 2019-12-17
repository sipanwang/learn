package controller;
/**
 * SpringBoot Security 入门Hello word
 * author: sipw
 * date: 2019/12/17
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("/index")////只接受get方式的请求
    @ResponseBody  //@ResponseBody的作用其实是将java对象转为json格式的数据
    public String index(){
        return "欢迎来到主界面";
    }

    @RequestMapping("/userlogin")
    public String userLogin(){
        return "login.html";
    }

    @GetMapping("/home")
    @ResponseBody
    public String home(){
        return "欢迎来到home界面";
    }

    //放行测试接口01，注入Security Config中，浏览器浏览器可以直接访问
    @GetMapping("/test01")
    @ResponseBody
    public String test01(){
        return "test01界面";
    }

    //放行测试接口02，不注入Security Config中，浏览器访问会跳转到配置的默认登陆界面
    @GetMapping("/test02")
    @ResponseBody
    public String test02(){
        return "test02界面";
    }

    //忽略拦截测试接口，注入Security Config中，浏览器可以直接访问
    @GetMapping("/test03")
    @ResponseBody
    public String test03(){
        return "test03界面";
    }
}
