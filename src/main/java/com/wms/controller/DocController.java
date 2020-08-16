package com.wms.controller;

import com.wms.annotation.NeedAuth;
import com.wms.bean.enums.ERole;
import com.wms.bean.request.CaseRequest;
import com.wms.bean.request.DocumentRequest;
import com.wms.service.ICaseService;
import com.wms.service.IDocumentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("doc")
@Api(tags = "涉案财物")
public class DocController {
    @Autowired
    ICaseService caseService;
    @Autowired
    IDocumentService documentService;


    @PostMapping("/case/search")
    @ApiOperation(value = "搜索案件")
    public void searchCase(){
    }

    @NeedAuth(roles = {ERole.BAMJ})
    @PostMapping("/case/save")
    @ApiOperation(value = "保存案件")
    public void saveCase(@RequestBody CaseRequest request){
        caseService.saveCase(request);
    }

    @PostMapping("/file/regist")
    @ApiOperation(value = "保存案件")
    public void fileRegist(@RequestBody DocumentRequest request){
        //documentService.regist(request);
    }
}
