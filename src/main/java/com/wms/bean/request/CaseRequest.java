package com.wms.bean.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "案件保存请求实体")
public class CaseRequest {
    @ApiModelProperty(value = "案件编号", notes = "AJ2020010100000001")
    private String number;
    @ApiModelProperty(value = "案件名称", notes = "关于XXX涉及盗窃的案件")
    private String name;
    @ApiModelProperty(value = "办案单位", notes = "上海市公安局杨浦分局")
    private String byOrg;
    @ApiModelProperty(value = "办案部门", notes = "刑侦科")
    private String byDept;
    @ApiModelProperty(value = "案件阶段", notes = "101：侦查阶段")
    private Integer stage;
    @ApiModelProperty(value = "案件种类", notes = "101：刑事案件")
    private Integer type;
}
