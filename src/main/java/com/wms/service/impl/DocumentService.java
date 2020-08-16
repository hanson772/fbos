package com.wms.service.impl;

import com.wms.bean.pojo.Employee;
import com.wms.bean.request.DocumentRequest;
import com.wms.dal.entity.DocumentDO;
import com.wms.dal.repository.DocumentRepository;
import com.wms.service.IDocumentService;
import com.wms.service.IProcessLogService;
import com.wms.util.Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class DocumentService implements IDocumentService {
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    IProcessLogService processLogService;

    /**
     * 登记涉案财物
     * @param e
     * @param request
     */
    @Override
    public void regist(Employee e, DocumentRequest request){
        Tools.checkFieldsIsBlank(request, "caseNumber", "name");

        DocumentDO document = DocumentDO.builder()
                .caseNumber(request.getCaseNumber())
                .name(request.getName())
                .caseNumber(createDocNumber(request.getCaseNumber()))
                .description(request.getDescription())
                .stage(request.getStage())
                .status(request.getStatus())
                .type(request.getType())
                .excolumn1(request.getEcolumn2())
                .build();

        document.setCreator(e.getName());
        document.setCreatorId(e.getUserId());
        documentRepository.saveAndFlush(document);

        // 操作日志记录
        processLogService.write(e, document.getCaseNumber(), document.getName(), "涉案财物登记");
    }

    /**
     * 生成物件编号
     * @param caseNumber
     * @return
     */
    private String createDocNumber(String caseNumber){
        return caseNumber + Tools.formatDate("yyyyMMhhHHmmss", new Date());
    }
}
