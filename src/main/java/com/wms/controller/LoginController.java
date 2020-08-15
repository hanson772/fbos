package com.wms.controller;

import com.alibaba.fastjson.JSONObject;
import com.wms.annotation.NeedAuth;
import com.wms.annotation.Who;
import com.wms.bean.ReturnVO;
import com.wms.bean.enums.ERole;
import com.wms.bean.pojo.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@Api(tags = "登录")
public class LoginController {

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
