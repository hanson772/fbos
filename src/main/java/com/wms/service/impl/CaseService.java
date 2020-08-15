package com.wms.service.impl;

import com.wms.bean.request.CaseRequest;
import com.wms.dal.entity.CaseDO;
import com.wms.dal.repository.CaseRepository;
import com.wms.service.ICaseService;
import com.wms.util.Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;

@Service
@Slf4j
public class CaseService implements ICaseService {
    @Autowired
    CaseRepository caseRepository;
    @Autowired
    EntityManager entityManager;

    /**
     * 保存案件信息
     * @param request
     */
    @Override
    public void saveCase(CaseRequest request){
        // 校验字段是否为空
        Tools.checkFieldsIsBlank(request, "number", "display", "byOrg", "byDept");
        /**
         * 校验是否案件编号重复
         */
        CaseDO caseDO = caseRepository.findByNumber(request.getNumber());
        Assert.isTrue(caseDO == null, "案件已存在");

        caseDO = CaseDO.builder()
                .number(request.getNumber())
                .display(request.getDisplay())
                .byOrg(request.getByOrg())
                .byDept(request.getByDept())
                .type(request.getType())
                .stage(request.getStage())
                .build();

        caseRepository.saveAndFlush(caseDO);
    }

}
