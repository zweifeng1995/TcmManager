/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.repository;


import com.gucheng.tcmmanager.model.entity.MedicinePropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-09-20
 */
@Repository
public interface MedicinePropertyRepo extends JpaRepository<MedicinePropertyEntity, String> {
    void deleteByMedicineEntityId(@Param("medicine_id") String medicineId);
}
