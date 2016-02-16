package com.calanger.yhzj.mobile.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.calanger.yhzj.bo.UserBO;
import com.calanger.yhzj.model.User;

import java.util.List;

@Controller
public class IndexController extends BaseController {
    public final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserBO userBO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request,Model model) {
        System.out.println("主页面");
        List<User> users = userBO.find();
        System.out.println(users);
        System.out.println(2);
        model.addAttribute("userlist", users);
        
      // 跳转页面
        return "/index/index";
    }
    
    public String getShortTitle(String title){
        return (title.length()>=20)?(title.substring(0, 65)+"..."):title;
    }
    
}
