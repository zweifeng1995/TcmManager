/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmbrain.TCMBrain.repo;


import com.gucheng.tcmbrain.model.entity.MedicineEntity;
import com.gucheng.tcmbrain.model.entity.MedicineExtendEntity;
import com.gucheng.tcmbrain.model.entity.MedicinePropertyEntity;
import com.gucheng.tcmbrain.repository.MedicineRepo;
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

        log.info("medicineRepo.findByCondition(\"\", \"\", \"\"): {}", medicineRepo.findByCondition("", "", "", new ArrayList<>()));
        Assertions.assertEquals(2, medicineRepo.findByCondition("", "", "", new ArrayList<>()).size());
    }

    private void generateData(String name) {
        MedicineEntity medicineEntity = new MedicineEntity();

        MedicineExtendEntity medicineExtendEntity = new MedicineExtendEntity();
        medicineExtendEntity.setNatureAndFlavor("NatureAndFlavor");
        medicineExtendEntity.setChannelTropism("ChannelTropism");
        medicineExtendEntity.setEfficacy("Efficacy");
        medicineExtendEntity.setClinicalApplication("ClinicalApplication");
        medicineExtendEntity.setMedicineEntity(medicineEntity);

        List<MedicinePropertyEntity> medicinePropertyEntities = new ArrayList<>();
        MedicinePropertyEntity medicinePropertyEntity1 = new MedicinePropertyEntity();
        medicinePropertyEntity1.setKind(0);
        medicinePropertyEntity1.setLevel(5);
        medicinePropertyEntity1.setMedicineEntity(medicineEntity);

        MedicinePropertyEntity medicinePropertyEntity2 = new MedicinePropertyEntity();
        medicinePropertyEntity2.setKind(1);
        medicinePropertyEntity2.setLevel(6);
        medicinePropertyEntity2.setMedicineEntity(medicineEntity);
        medicinePropertyEntities.add(medicinePropertyEntity1);
        medicinePropertyEntities.add(medicinePropertyEntity2);

        medicineEntity.setName(name);
        medicineEntity.setMmpResearch("mmpResearch");
        medicineEntity.setMedicineExtendEntity(medicineExtendEntity);
        medicineEntity.setProperties(medicinePropertyEntities);
        medicineRepo.save(medicineEntity);
    }
}
