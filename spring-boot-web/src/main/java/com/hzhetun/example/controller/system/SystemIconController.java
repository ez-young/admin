package com.hzhetun.example.controller.system;

import com.hzhetun.example.service.SystemIconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author cbk
 * @date 2017/12/23
 */
@Controller
public class SystemIconController {
    @Autowired
    private SystemIconService systemIconService;
}
