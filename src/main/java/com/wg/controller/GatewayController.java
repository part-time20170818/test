package com.wg.controller;

import com.wg.dto.GatewayRequestBO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by xiewenge on 2017/7/21.
 */
@Controller
@RequestMapping(value = "/gateway", method = { RequestMethod.POST, RequestMethod.GET })
public class GatewayController {
    @ResponseBody
    @RequestMapping(value = "", method = { RequestMethod.POST, RequestMethod.GET })
    public String gateway(@Valid GatewayRequestBO requestBO, BindingResult bindingResult, HttpServletResponse response) throws ServletException, IOException {
        return "nishisnbhkshi";
    }

}
