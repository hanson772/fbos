package com.wms.service.impl;

import com.wms.bean.pojo.Employee;
import com.wms.dal.entity.ProcessLogDO;
import com.wms.dal.repository.ProcessLogRepository;
import com.wms.service.IProcessLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProcessLogService implements IProcessLogService {
    @Autowired
    ProcessLogRepository processLogRepository;

    /**
     * 操作记录
     *
     * @param e
     * @param caseNo
     * @param docNo
     * @param action
     */
    @Override
    public void write(Employee e, String caseNo, String docNo, String action) {
        processLogRepository.saveAndFlush(
                ProcessLogDO.builder()
                        .caseNumber(caseNo)
                        .docNumber(docNo)
                        .operator(e.getOperator())
                        .action(action)
                        .build());
    }

    @Override
    public List<ProcessLogDO> getAllByCaseNo(String caseNo) {
        return processLogRepository.findByCaseNumberOrderByIdDesc(caseNo);
    }

    @Override
    public List<ProcessLogDO> getAllByDocNo(String docNo) {
        return processLogRepository.findByDocNumberOrderByIdDesc(docNo);
    }
}
