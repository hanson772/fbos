package com.wms.dal.repository;

import com.wms.dal.entity.DocumentDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author max.chen
 * @class
 */
public interface DocumentRepository extends JpaRepository<DocumentDO, Integer> {
    DocumentDO findByNumber(@Param("number") String number);
}