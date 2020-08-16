package com.wms.bean.pojo;

import com.wms.bean.enums.ERole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author max.chen
 * @class
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "登录用户实体", description = "登录用户实体")
public class Employee {
    @ApiModelProperty(value = "用户身份ID", notes = "4A账户返回用户主键")
    String userId;
    @ApiModelProperty(value = "用户姓名", notes = "4A账户返回用户姓名")
    String name;
    @ApiModelProperty(value = "用户所在单位", notes = "4A账户返回用户所在单位")
    String org;
    @ApiModelProperty(value = "用户所在部门", notes = "4A账户返回用户所在部门")
    String dept;
    @ApiModelProperty(value = "用户职位", notes = "4A账户返回用户职位")
    String job;
    @ApiModelProperty(value = "用户角色", notes = "4A账户返回用户权限角色")
    ERole[] roles;


    public String getOperator(){
        return this.name + "-" + this.getUserId();
    }
}
