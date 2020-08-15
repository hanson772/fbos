package com.wms.dal.repository;

import com.wms.dal.entity.CaseDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author max.chen
 * @class
 */
public interface CaseRepository extends JpaRepository<CaseDO, Integer> {
    CaseDO findByNumber(@Param("number") String number);
}