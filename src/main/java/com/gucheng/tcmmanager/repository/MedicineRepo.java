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
            " AND EXISTS (SELECT 1 FROM medicine_extend me WHERE me.medicine_id = m.id AND me.efficacy LIKE %:efficacy%)" +
            " AND EXISTS (SELECT 1 FROM medicine_extend me WHERE me.medicine_id = m.id AND me.clinical_application LIKE %:clinical_application%)" +
            " AND EXISTS (SELECT 1 FROM medicine_property mp WHERE mp.medicine_id = m.id AND mp.kind IN (:mdc_prop_kinds))",
            nativeQuery = true)
    List<MedicineEntity> findByCondition(@Param("name") String name,
                                         @Param("efficacy") String efficacy,
                                         @Param("clinical_application") String clinicalApplication,
                                         @Param("mdc_prop_kinds") List<Integer> mdcPropKindList);

    MedicineEntity findByName(@Param("name") String name);
}
