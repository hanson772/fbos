package com.wms.controller;

import com.wms.bean.ReturnVO;
import com.wms.service.IAttachService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author max.chen
 * @class
 */
@Slf4j
@RestController
@RequestMapping("attach")
@Api(tags = "附件")
public class AttachController {
    @Autowired
    IAttachService attachService;
    /********************投递阶段**********************/

    @PostMapping("/upload")
    @ApiOperation(value = "上传附件")
    public ReturnVO upload(@RequestParam("file") MultipartFile file) {
        attachService.upload(file);
        return ReturnVO.success();
    }
}
