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
@Table(name = "wms_case",
        indexes = {
                //@Index(name = "IDX_case_no", columnList = "case_no")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"case_no"})
        }
)
@org.hibernate.annotations.Table(appliesTo = "wms_case", comment = "案件表")
public class CaseDO extends AuditingDO{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "case_no", columnDefinition = "nvarchar(200) NOT NULL COMMENT '案件编号'")
    private String number;

    @Column(name = "case_name", columnDefinition = "nvarchar(200) NOT NULL COMMENT '案件名称'")
    private String name;

    @Column(name = "operate_by_org", columnDefinition = "nvarchar(200) NOT NULL COMMENT '办案单位'")
    private String byOrg;

    @Column(name = "operate_by_dept", columnDefinition = "nvarchar(200) NULL COMMENT '办案部门'")
    private String byDept;

    @Column(name = "stage", columnDefinition = "smallint(4) NULL COMMENT '案件阶段'")
    private Integer stage;

    @Column(name = "type", columnDefinition = "smallint(4) NULL COMMENT '案件种类'")
    private Integer type;
}
