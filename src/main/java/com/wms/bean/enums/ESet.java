package com.wms.bean.enums;

/**
 * 基础数据
 * @author max.chen
 * @class
 */
public enum ESet {
    Location(101, "location", "地点"),
    ;

    int type;
    String code;
    String display;

    ESet(int type, String code, String display) {
        this.type = type;
        this.code = code;
        this.display = display;
    }

    public int getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public String getDisplay() {
        return display;
    }
}
