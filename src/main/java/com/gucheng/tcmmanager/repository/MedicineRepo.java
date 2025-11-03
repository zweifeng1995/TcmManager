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
            " WHERE m.name LIKE %:name%" +
            " AND m.mmp_research LIKE %:mmp_research%" +
            " AND (:first_category = '' OR m.first_category = :first_category)" +
            " AND (:second_category = '' OR m.second_category = :second_category)" +
            " AND EXISTS (SELECT 1 FROM medicine_extend me WHERE me.medicine_id = m.id AND me.efficacy LIKE %:efficacy%)" +
            " AND EXISTS (SELECT 1 FROM medicine_extend me WHERE me.medicine_id = m.id AND me.clinical_application LIKE %:clinical_application%)" +
            " AND EXISTS (SELECT 1 FROM medicine_extend me WHERE me.medicine_id = m.id AND me.nature_and_flavor LIKE %:nature_and_flavor%)" +
            " AND EXISTS (SELECT 1 FROM medicine_extend me WHERE me.medicine_id = m.id AND me.channel_tropism LIKE %:channel_tropism%)",
            nativeQuery = true)
    List<MedicineEntity> findByCondition(@Param("name") String name,
                                         @Param("mmp_research") String mmpResearch,
                                         @Param("efficacy") String efficacy,
                                         @Param("clinical_application") String clinicalApplication,
                                         @Param("first_category") String firstCategory,
                                         @Param("second_category") String secondCategory,
                                         @Param("nature_and_flavor") String natureAndFlavor,
                                         @Param("channel_tropism") String channelTropism);

    MedicineEntity findByName(@Param("name") String name);
}
