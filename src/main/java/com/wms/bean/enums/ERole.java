package com.wms.bean.enums;

import java.util.Arrays;
import java.util.List;

public enum  ERole implements IEBase<ERole>{
    GM(20, "应用管理员"),
    BAMJ(12, "办案民警"),
    ZGY(14, "专管员"),
    BGY(16, "保管员"),
    CXYH(8, "查询用户"),
    ;
    int code;
    String name;

    ERole(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 校验roles中是否包含任意一个pops
     *
     * @param need
     * @param roles
     */
    public static boolean anyMatch(ERole[] need, ERole[] roles) {
        if(need == null || need.length == 0){
            return true;
        }

        List<ERole> needRoles = Arrays.asList(need);
        for(int i = 0; i < roles.length; i++){
            if(needRoles.contains(roles[i])){
                return true;
            }
        }
        return false;
    }
}
