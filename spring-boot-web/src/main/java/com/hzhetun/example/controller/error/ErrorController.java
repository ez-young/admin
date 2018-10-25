package com.hzhetun.example.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cbk
 * @date 2017/12/23
 */
@Controller
public class ErrorController {
    @RequestMapping(value = "/404")
    public String notfound(){
        return "error/404";
    }
}
