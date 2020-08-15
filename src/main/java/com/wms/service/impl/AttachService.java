package com.wms.service.impl;

import com.wms.service.IAttachService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author max.chen
 * @class
 */
@Service
@Slf4j
public class AttachService implements IAttachService {
    /**
     * 上传附件并保存
     * @param file
     * @return
     */
    @Override
    public void upload(MultipartFile file){

        //TODO 上传附件
        String dispay = file.getOriginalFilename();
        String mime = file.getContentType();
        long size = file.getSize();
        String code= uploadToDisk(file);
    }

    /**
     * 附件预览
     * @param aId
     * @param response
     */
    @Override
    public void preview(int aId, HttpServletResponse response){

        // TODO 文件预览
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    private String uploadToDisk(MultipartFile file){
        //TODO
        return UUID.randomUUID().toString().replace("-", "");
    }
}
