/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmbrain.repository;


import com.gucheng.tcmbrain.model.entity.MedicineEntity;
import com.gucheng.tcmbrain.model.entity.PrescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-09-20
 */
@Repository
public interface PrescriptionRepo extends JpaRepository<PrescriptionEntity, String> {
}
