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
@Table(name = "wms_item",
        indexes = {
                @Index(name = "IDX_case_no", columnList = "case_no"),
                @Index(name = "IDX_item_no", columnList = "item_no")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"item_no"})
        }
)
@org.hibernate.annotations.Table(appliesTo = "wms_item", comment = "涉案物件表")
public class ItemDO extends AuditingDO{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "case_no", columnDefinition = "nvarchar(200) NOT NULL COMMENT '案件编号'")
    private Integer caseNo;

    @Column(name = "item_no", columnDefinition = "nvarchar(200) NOT NULL COMMENT '涉案物件编号'")
    private String number;

    @Column(name = "item_type", columnDefinition = "smallint(4) NOT NULL COMMENT '涉案物件类型'")
    private Integer type;

    @Column(name = "item_name", columnDefinition = "nvarchar(200) NOT NULL COMMENT '涉案物件名称'")
    private String display;

    @Column(name = "item_desc", columnDefinition = "text(2000) NULL COMMENT '涉案物件描述'")
    private String description;

    @Column(name = "stage", columnDefinition = "smallint(4) NULL COMMENT '涉案物件所属阶段'")
    private Integer stage;

    @Column(name = "status", columnDefinition = "smallint(4) NULL COMMENT '涉案物件所属状态'")
    private Integer status;
}
