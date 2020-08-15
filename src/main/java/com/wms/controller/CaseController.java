package com.wms.controller;

import com.wms.bean.request.CaseRequest;
import com.wms.service.ICaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("case")
@Api(tags = "案件")
public class CaseController {
    @Autowired
    ICaseService caseService;


    @PostMapping("/search")
    @ApiOperation(value = "搜索案件")
    public void searchCase(){

    }

    @PostMapping("/save")
    @ApiOperation(value = "保存案件")
    public void saveCase(@RequestBody CaseRequest request){
        caseService.saveCase(request);
    }
}
