/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.repository;


import com.gucheng.tcmmanager.model.entity.MedicineExtendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-09-20
 */
@Repository
public interface MedicineExtendRepo extends JpaRepository<MedicineExtendEntity, String> {
    Optional<MedicineExtendEntity> findByMedicineEntityId(@Param("medicine_id") String medicineId);
}
