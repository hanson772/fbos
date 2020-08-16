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
@ApiModel(value = "涉案财物保存请求实体")
public class DocumentRequest {
    @ApiModelProperty(value = "案件编号", notes = "AJ2020010100000001")
    private String caseNumber;
    @ApiModelProperty(value = "物件名称", notes = "关于XXX涉及盗窃的案件")
    private String name;
    @ApiModelProperty(value = "物件明细", notes = "xxxx")
    private String description;
    @ApiModelProperty(value = "物件阶段", notes = "")
    private Integer stage;
    @ApiModelProperty(value = "物件状态", notes = "")
    private Integer status;
    @ApiModelProperty(value = "物件类型", notes = "")
    private Integer type;
    @ApiModelProperty(value = "额外描述1", notes = "")
    private String ecolumn1;
    @ApiModelProperty(value = "额外描述2", notes = "")
    private String ecolumn2;
    @ApiModelProperty(value = "额外描述3", notes = "")
    private String ecolumn3;
    @ApiModelProperty(value = "额外描述4", notes = "")
    private String ecolumn4;
    @ApiModelProperty(value = "额外描述5", notes = "")
    private String ecolumn5;
}
