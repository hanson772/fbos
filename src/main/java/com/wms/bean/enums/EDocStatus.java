package com.wms.bean.enums;

/**
 * 涉案物件所属状态
 */
public enum EDocStatus implements IEBase<EDocStatus>{
    Stage1(11, "取证"),

    ;
    int code;
    String name;

    EDocStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
