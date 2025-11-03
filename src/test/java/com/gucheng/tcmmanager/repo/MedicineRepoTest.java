/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.repo;


import com.gucheng.tcmmanager.model.entity.MedicineEntity;
import com.gucheng.tcmmanager.model.entity.MedicineExtendEntity;
import com.gucheng.tcmmanager.model.entity.MedicinePropertyEntity;
import com.gucheng.tcmmanager.repository.MedicineRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
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
public class MedicineRepoTest {
    @Autowired
    private MedicineRepo medicineRepo;

    @Test
    public void test() {
//        MedicineEntity medicineEntity = new MedicineEntity();
//
//        MedicineExtendEntity medicineExtendEntity = new MedicineExtendEntity();
//        medicineExtendEntity.setMedicineEntity(medicineEntity);
//        medicineExtendEntity.setNatureAndFlavor("NatureAndFlavor");
//        medicineExtendEntity.setChannelTropism("ChannelTropism");
//        medicineExtendEntity.setEfficacy("Efficacy");
//        medicineExtendEntity.setClinicalApplication("ClinicalApplication");
//
//        List<MedicinePropertyEntity> medicinePropertyEntities = new ArrayList<>();
//        MedicinePropertyEntity medicinePropertyEntity1 = new MedicinePropertyEntity();
//        medicinePropertyEntity1.setKind(0);
//        medicinePropertyEntity1.setLevel(5);
//        medicinePropertyEntity1.setMedicineEntity(medicineEntity);
//
//        MedicinePropertyEntity medicinePropertyEntity2 = new MedicinePropertyEntity();
//        medicinePropertyEntity2.setKind(1);
//        medicinePropertyEntity2.setLevel(6);
//        medicinePropertyEntity2.setMedicineEntity(medicineEntity);
//
//        medicinePropertyEntities.add(medicinePropertyEntity1);
//        medicinePropertyEntities.add(medicinePropertyEntity2);
//
//        medicineEntity.setName("Medicine");
//        medicineEntity.setMmpResearch("MmpResearch");
//        medicineEntity.setMedicineExtendEntity(medicineExtendEntity);
//        medicineEntity.setProperties(medicinePropertyEntities);
//
//        medicineRepo.save(medicineEntity);
        generateData("Medicine1");
        generateData("Medicine2");

        log.info("medicineRepo.findAll(): {}", medicineRepo.findAll());
        Assertions.assertEquals(2, medicineRepo.findAll().size());

        log.info("medicineRepo.findByCondition(\"\", \"\", \"\"): {}", medicineRepo.findByCondition("", "","", "", "", "", "", ""));
        Assertions.assertEquals(2, medicineRepo.findByCondition("", "","", "", "", "", "", "").size());
    }

    private void generateData(String name) {
        MedicineEntity medicineEntity = new MedicineEntity();

        MedicineExtendEntity medicineExtendEntity = new MedicineExtendEntity();
        medicineExtendEntity.setMedicineEntity(medicineEntity);
        medicineExtendEntity.setNatureAndFlavor("NatureAndFlavor");
        medicineExtendEntity.setChannelTropism("ChannelTropism");
        medicineExtendEntity.setEfficacy("Efficacy");
        medicineExtendEntity.setClinicalApplication("ClinicalApplication");

        MedicinePropertyEntity medicinePropertyEntity1 = new MedicinePropertyEntity();
        medicinePropertyEntity1.setMedicineEntity(medicineEntity);
        medicinePropertyEntity1.setKind(0);
        medicinePropertyEntity1.setLevel(5);

        MedicinePropertyEntity medicinePropertyEntity2 = new MedicinePropertyEntity();
        medicinePropertyEntity2.setMedicineEntity(medicineEntity);
        medicinePropertyEntity2.setKind(1);
        medicinePropertyEntity2.setLevel(6);

        List<MedicinePropertyEntity> medicinePropertyEntities = new ArrayList<>();
        medicinePropertyEntities.add(medicinePropertyEntity1);
        medicinePropertyEntities.add(medicinePropertyEntity2);

        medicineEntity.setName(name);
        medicineEntity.setFirstCategory("firstCategory");
        medicineEntity.setSecondCategory("secondCategory");
        medicineEntity.setMmpResearch("mmpResearch");
        medicineEntity.setMedicineExtendEntity(medicineExtendEntity);
        medicineEntity.setProperties(medicinePropertyEntities);
        medicineRepo.save(medicineEntity);
    }
}
