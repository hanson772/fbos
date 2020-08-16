package com.wms.bean.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum  ERole implements IEBase<ERole>{
    GM(20, "应用管理员", "gm"),
    BAMJ(12, "办案民警", "bnmj"),
    ZGY(14, "专管员", "zgy"),
    BGY(16, "保管员", "bgy"),
    CXYH(8, "查询用户", "cxyh"),
    ;
    int code;
    String name;
    String userCode;

    ERole(int code, String name, String userCode) {
        this.code = code;
        this.name = name;
        this.userCode = userCode;
    }

    public String getUserCode(){
        return this.userCode;
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

    /**
     * 返回Map格式，key=usercode
     * @return
     */
    public static Map<String, ERole> mapByUserCode(){
        return Arrays.asList(ERole.values()).stream().collect(Collectors.toMap(ERole::getUserCode, Function.identity()));
    }
}
