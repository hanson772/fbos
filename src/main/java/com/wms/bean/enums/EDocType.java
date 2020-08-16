package com.wms.bean.enums;

/**
 * 涉案物件所属类型财/物
 */
public enum EDocType implements IEBase<EDocType>{
    MAT(1, "涉案物件"),
    FIN(3, "涉案财产"),
    ;
    int code;
    String name;

    EDocType(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
