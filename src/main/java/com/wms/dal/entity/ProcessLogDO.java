package com.wms.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "wms_process_log",
        indexes = {
                @Index(name = "IDX_case_no", columnList = "case_no"),
                @Index(name = "IDX_doc_no", columnList = "doc_no")
        },
        uniqueConstraints = {
        }
)
@org.hibernate.annotations.Table(appliesTo = "wms_process_log", comment = "操作记录表")
public class ProcessLogDO{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "case_no", columnDefinition = "nvarchar(200) NOT NULL COMMENT '案件编号'")
    private String caseNumber;

    @Column(name = "doc_no", columnDefinition = "nvarchar(200) NOT NULL COMMENT '案件编号'")
    private String docNumber;

    @Column(name = "operator", columnDefinition = "nvarchar(200) NOT NULL COMMENT '操作人'")
    private String operator;

    @Column(name = "operate_action", columnDefinition = "nvarchar(200) NULL COMMENT '操作事件'")
    private String action;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间'")
    @CreatedDate
    private Date created;

    @Column(name = "type", columnDefinition = "smallint(4) NULL COMMENT '操作性质种类'")
    private Integer type;
}
