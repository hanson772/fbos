package com.wms.service;

import com.wms.bean.pojo.Employee;
import com.wms.bean.request.DocumentRequest;

public interface IDocumentService {
    void regist(Employee e, DocumentRequest request);
}
