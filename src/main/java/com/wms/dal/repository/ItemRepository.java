package com.wms.dal.repository;

import com.wms.dal.entity.ItemDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author max.chen
 * @class
 */
public interface ItemRepository extends JpaRepository<ItemDO, Integer> {
}