package com.how2java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * com.how2java
 *
 * @author jh
 * @date 2018/7/25 10:34
 * description:
 */
@Controller


public class LoginController {

    @RequestMapping("/login")
    public String login(HttpSession session,String username, String password)throws Exception{
    System.out.println ("----->login");
    session.setAttribute ("username",username);
        return "redirect:/items/queryItems";
    }
@RequestMapping("/logout")
    public String logout(HttpSession session)throws Exception{

        session.invalidate ();
        return "redirect:/items/queryItems";
    }
}
