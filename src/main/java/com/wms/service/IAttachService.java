package com.wms.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author max.chen
 * @class
 */
public interface IAttachService {
    void upload(MultipartFile file);

    void preview(int aId, HttpServletResponse response);
}
