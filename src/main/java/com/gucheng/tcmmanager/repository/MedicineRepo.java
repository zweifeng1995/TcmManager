/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.repository;


import com.gucheng.tcmmanager.model.entity.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-09-20
 */
@Repository
public interface MedicineRepo extends JpaRepository<MedicineEntity, String> {
    @Query(value = "SELECT m.* FROM medicine m" +
            " WHERE (:first_category = '' OR m.first_category = :first_category) AND (:second_category = '' OR m.second_category = :second_category)" +
            " AND (m.name LIKE %:all%" +
            " OR m.mmp_research LIKE %:all%" +
            " OR EXISTS (SELECT 1 FROM medicine_extend me WHERE me.medicine_id = m.id AND me.efficacy LIKE %:all%)" +
            " OR EXISTS (SELECT 1 FROM medicine_extend me WHERE me.medicine_id = m.id AND me.clinical_application LIKE %:all%)" +
            " OR EXISTS (SELECT 1 FROM medicine_extend me WHERE me.medicine_id = m.id AND me.nature_and_flavor LIKE %:all%)" +
            " OR EXISTS (SELECT 1 FROM medicine_extend me WHERE me.medicine_id = m.id AND me.channel_tropism LIKE %:all%))",
            nativeQuery = true)
    List<MedicineEntity> findByCondition(@Param("all") String all,
                                         @Param("first_category") String firstCategory,
                                         @Param("second_category") String secondCategory);

    MedicineEntity findByName(@Param("name") String name);
}
