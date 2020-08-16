package com.wms.service;

import com.wms.bean.pojo.Employee;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {
    String loginByTicket(String ticket);

    Employee getUserFromRequest(HttpServletRequest request);
}
