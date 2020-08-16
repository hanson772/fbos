package com.wms.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "wms_document",
        indexes = {
                @Index(name = "IDX_case_no", columnList = "case_no")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"doc_no"})
        }
)
@org.hibernate.annotations.Table(appliesTo = "wms_document", comment = "涉案物件表")
public class DocumentDO extends AuditingDO{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "case_no", columnDefinition = "nvarchar(200) NOT NULL COMMENT '案件编号'")
    private String caseNumber;

    @Column(name = "doc_no", columnDefinition = "nvarchar(200) NOT NULL COMMENT '案件-物件编号'")
    private String number;

    @Column(name = "doc_name", columnDefinition = "nvarchar(200) NOT NULL COMMENT '物件名称'")
    private String name;

    @Column(name = "doc_desc", columnDefinition = "text(2000) NULL COMMENT '物件明细'")
    private String description;

    @Column(name = "stage", columnDefinition = "smallint(4) NULL COMMENT '物件阶段'")
    private Integer stage;

    @Column(name = "status", columnDefinition = "smallint(4) NULL COMMENT '物件状态'")
    private Integer status;

    @Column(name = "type", columnDefinition = "smallint(4) NULL COMMENT '物件种类'")
    private Integer type;

    @Column(name = "ex_column1", columnDefinition = "nvarchar(200) NULL COMMENT '备用字段'")
    private String excolumn1;

    @Column(name = "ex_column2", columnDefinition = "nvarchar(200) NULL COMMENT '备用字段'")
    private String excolumn2;

    @Column(name = "ex_column3", columnDefinition = "nvarchar(200) NULL COMMENT '备用字段'")
    private String excolumn3;

    @Column(name = "ex_column4", columnDefinition = "nvarchar(200) NULL COMMENT '备用字段'")
    private String excolumn4;

    @Column(name = "ex_column5", columnDefinition = "nvarchar(200) NULL COMMENT '备用字段'")
    private String excolumn5;
}
