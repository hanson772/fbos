package com.wms.config;

import lombok.Builder;
import lombok.Data;

/**
 * @author max.chen
 * @class
 */
@Data
@Builder
public class CusException extends RuntimeException {
    final static int ERROR_CODE = 1;
    int code;
    String message;
    Throwable thrown;

    public CusException(String message){
        super(message);
        this.code = ERROR_CODE;
    }

    public CusException(int code, String message){
        super(message);
        this.code = code;
    }
    public CusException(String message, Throwable e){
        super(message, e);
        this.code = ERROR_CODE;
    }
    public CusException(int code, String message, Throwable e){
        super(message, e);
        this.code = code;
    }

}
