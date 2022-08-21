package org.example.thinking.in.spring.ioc.bean.scope.web.controller;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;

/**
 * mvc首页
 */

@Controller
public class IndexController {
    @Autowired
    private User user;//cblib代理的对象不变的

    @GetMapping("index.html")
    public String index(Model model){
        model.addAttribute("userObject",user);
        return "index";
    }

}
