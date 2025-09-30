/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmbrain.TCMBrain.repo;


import com.gucheng.tcmbrain.model.entity.MedicineEntity;
import com.gucheng.tcmbrain.model.entity.MedicineExtendEntity;
import com.gucheng.tcmbrain.model.entity.MedicinePropertyEntity;
import com.gucheng.tcmbrain.model.entity.PrescriptionEntity;
import com.gucheng.tcmbrain.repository.MedicineRepo;
import com.gucheng.tcmbrain.repository.PrescriptionRepo;
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
        MedicineEntity medicineEntity1 = new MedicineEntity();

        MedicineExtendEntity medicineExtendEntity1 = new MedicineExtendEntity();
        medicineExtendEntity1.setMedicineEntity(medicineEntity1);
        medicineExtendEntity1.setNatureAndFlavor("NatureAndFlavor");
        medicineExtendEntity1.setChannelTropism("ChannelTropism");
        medicineExtendEntity1.setEfficacy("Efficacy");
        medicineExtendEntity1.setClinicalApplication("ClinicalApplication");

        List<MedicinePropertyEntity> medicinePropertyEntities1 = new ArrayList<>();
        MedicinePropertyEntity medicinePropertyEntity1 = new MedicinePropertyEntity();
        medicinePropertyEntity1.setMedicineEntity(medicineEntity1);
        medicinePropertyEntity1.setKind(0);
        medicinePropertyEntity1.setLevel(5);

        MedicinePropertyEntity medicinePropertyEntity2 = new MedicinePropertyEntity();
        medicinePropertyEntity2.setMedicineEntity(medicineEntity1);
        medicinePropertyEntity2.setKind(0);
        medicinePropertyEntity2.setLevel(5);

        medicinePropertyEntities1.add(medicinePropertyEntity1);
        medicinePropertyEntities1.add(medicinePropertyEntity2);

        medicineEntity1.setName("Medicine1");
        medicineEntity1.setMedicineExtendEntity(medicineExtendEntity1);
        medicineEntity1.setProperties(medicinePropertyEntities1);

        medicineRepo.save(medicineEntity1);

        MedicineEntity medicineEntity2 = new MedicineEntity();

        MedicineExtendEntity medicineExtendEntity2 = new MedicineExtendEntity();
        medicineExtendEntity2.setMedicineEntity(medicineEntity2);
        medicineExtendEntity2.setNatureAndFlavor("NatureAndFlavor");
        medicineExtendEntity2.setChannelTropism("ChannelTropism");
        medicineExtendEntity2.setEfficacy("Efficacy");
        medicineExtendEntity2.setClinicalApplication("ClinicalApplication");

        List<MedicinePropertyEntity> medicinePropertyEntities2 = new ArrayList<>();
        MedicinePropertyEntity medicinePropertyEntity3 = new MedicinePropertyEntity();
        medicinePropertyEntity3.setMedicineEntity(medicineEntity2);
        medicinePropertyEntity3.setKind(0);
        medicinePropertyEntity3.setLevel(5);

        MedicinePropertyEntity medicinePropertyEntity4 = new MedicinePropertyEntity();
        medicinePropertyEntity4.setMedicineEntity(medicineEntity2);
        medicinePropertyEntity4.setKind(0);
        medicinePropertyEntity4.setLevel(5);

        medicinePropertyEntities1.add(medicinePropertyEntity3);
        medicinePropertyEntities1.add(medicinePropertyEntity4);

        medicineEntity2.setName("Medicine2");
        medicineEntity2.setMmpResearch("MmpResearch");
        medicineEntity2.setMedicineExtendEntity(medicineExtendEntity2);
        medicineEntity2.setProperties(medicinePropertyEntities2);
        medicineRepo.save(medicineEntity2);

        List<MedicineEntity> medicineEntities1 = new ArrayList<>();
        medicineEntities1.add(medicineEntity1);
        medicineEntities1.add(medicineEntity2);
        PrescriptionEntity prescriptionEntity1 = new PrescriptionEntity();
        prescriptionEntity1.setName("Prescription1");
        prescriptionEntity1.setMedicines(medicineEntities1);

        prescriptionRepo.save(prescriptionEntity1);

        List<MedicineEntity> medicineEntities2 = new ArrayList<>();
        medicineEntities2.add(medicineEntity1);
        medicineEntities2.add(medicineEntity2);
        PrescriptionEntity prescriptionEntity2 = new PrescriptionEntity();
        prescriptionEntity2.setName("Prescription2");
        prescriptionEntity2.setMedicines(medicineEntities2);
        prescriptionRepo.save(prescriptionEntity2);

        System.out.println(prescriptionRepo.findAll());
        log.info("medicineDao.findAll():{}", prescriptionRepo.findAll().toArray());
    }
}
