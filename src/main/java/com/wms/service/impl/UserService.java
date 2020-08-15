package com.wms.service.impl;

import com.wms.bean.enums.ERole;
import com.wms.bean.pojo.Employee;
import com.wms.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class UserService implements IUserService {
    /**
     * 根据请求获取当前登录人的身份信息ß
     *
     * @param request
     * @return
     */
    @Override
    public Employee getUserFromRequest(HttpServletRequest request) {
        //TODO
        String role = request != null ? request.getHeader("x-role") : "";
        role = StringUtils.hasText(role) ? role.toLowerCase() : "";
        ERole[] roles = null;
        switch (role) {
            case "gm":
                roles = new ERole[]{ERole.GM};
                break;
            case "bamj":
                roles = new ERole[]{ERole.BAMJ};
                break;
            case "zgy":
                roles = new ERole[]{ERole.ZGY};
                break;
            case "bgy":
                roles = new ERole[]{ERole.BGY};
                break;
            case "cxyh":
                roles = new ERole[]{ERole.CXYH};
                break;
            default:
                break;
        }
        if(roles == null || roles.length == 0){
            return null;
        }
        return Employee.builder()
                .userId("x0001")
                .name("张无忌")
                .email("wuji.zhang@shga.com")
                .org("上海市公安局")
                .dept("档案科")
                .job("民警")
                .roles(roles)
                .build();
    }
}
