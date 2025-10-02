/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.repo;


import com.gucheng.tcmmanager.model.entity.MedicineEntity;
import com.gucheng.tcmmanager.model.entity.MedicineExtendEntity;
import com.gucheng.tcmmanager.model.entity.MedicinePropertyEntity;
import com.gucheng.tcmmanager.model.entity.PrescriptionEntity;
import com.gucheng.tcmmanager.repository.MedicineRepo;
import com.gucheng.tcmmanager.repository.PrescriptionRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-09-20
 */
@Slf4j
@DataJpaTest
public class PrescriptionRepoTest {
    @Autowired
    private PrescriptionRepo prescriptionRepo;

    @Autowired
    private MedicineRepo medicineRepo;

    @Test
    public void test() {
    }
}
