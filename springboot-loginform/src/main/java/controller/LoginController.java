package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;


import java.util.Map;

@Controller
public class LoginController {

    //@DeleteMapping
    //@PutMapping
    //@GetMapping
    //@RequestMapping(value = "/usr/login", method = RequestMethod.POST)

    @PostMapping(value = "/loginAction")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map){

        if(!StringUtils.isEmpty(username) && "123456".equals(password)){

            return "success";
        }

        map.put("msg", "用户名密码错误");
        return "error";
    }

    //注册一个登陆失败页面
    @RequestMapping("/loginError")
    public String loginError(){
        return "error";
    }

    @RequestMapping("/helloTest")
    public String helloWord(){
        return "Hello Spring Security";
    }
}
