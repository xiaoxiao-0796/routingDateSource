package com.xiaofei.dataSource.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/12/16 0016.
 */
@Controller
@Slf4j
public class UserController {



    @RequestMapping("/sss.html")
    public String sss(){
        return "123";
    }
}
