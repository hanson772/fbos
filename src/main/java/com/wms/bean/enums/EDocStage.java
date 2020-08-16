package com.wms.bean.enums;

/**
 * 涉案物件所属阶段
 */
public enum EDocStage implements IEBase<EDocStage>{
    Stage1(11, "取证"),

    ;
    int code;
    String name;

    EDocStage(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
