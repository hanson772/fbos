package com.wms.controller;

import com.alibaba.fastjson.JSONObject;
import com.wms.annotation.NeedAuth;
import com.wms.annotation.Who;
import com.wms.bean.ReturnVO;
import com.wms.bean.enums.ERole;
import com.wms.bean.pojo.Employee;
import com.wms.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("login")
@Api(tags = "登录")
public class LoginController {
    @Autowired
    IUserService userService;
    final String User_Header = "x-token";


    @GetMapping("/ticket")
    @ApiOperation(value = "SSO登录")
    public void ticket(@Param("ticket") String ticket, HttpServletResponse response) {
        String token = userService.loginByTicket(ticket);
        Cookie cookie = new Cookie(User_Header, token);
        cookie.setPath("/");
        response.addCookie(cookie);

        //return new ModelAndView(new RedirectView(redirect, true), null);
    }


    @GetMapping("/info")
    @ApiOperation(value = "当前身份信息")
    public ReturnVO info(@Who Employee e) {
        return ReturnVO.success("当前验证角色是 ：" + JSONObject.toJSONString(e));
    }

    @NeedAuth(roles = {ERole.GM})
    @GetMapping("/test/auth")
    @ApiOperation(value = "测试AAuth")
    public ReturnVO testAuth(@Who Employee e) {
        return ReturnVO.success("当前验证角色是 ：" + JSONObject.toJSONString(e));
    }
}
