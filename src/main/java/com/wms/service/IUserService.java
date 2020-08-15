package com.wms.service;

import com.wms.bean.pojo.Employee;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {
    Employee getUserFromRequest(HttpServletRequest request);
}
