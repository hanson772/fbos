package com.wms.service;

import com.wms.bean.pojo.Employee;
import com.wms.dal.entity.ProcessLogDO;

import java.util.List;

public interface IProcessLogService {
    void write(Employee e, String caseNo, String docNo, String action);

    List<ProcessLogDO> getAllByCaseNo(String caseNo);

    List<ProcessLogDO> getAllByDocNo(String docNo);
}
