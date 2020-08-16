package com.wms.dal.repository;

import com.wms.dal.entity.ProcessLogDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author max.chen
 * @class
 */
public interface ProcessLogRepository extends JpaRepository<ProcessLogDO, Integer> {
    List<ProcessLogDO> findByCaseNumberOrderByIdDesc(@Param("caseNumber") String caseNumber);

    List<ProcessLogDO> findByDocNumberOrderByIdDesc(@Param("docNumber") String docNumber);
}