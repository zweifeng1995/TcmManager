/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.dao;


import com.gucheng.tcmmanager.model.dto.PrescriptionDTO;
import com.gucheng.tcmmanager.model.entity.PrescriptionEntity;
import com.gucheng.tcmmanager.model.entity.PrescriptionMdcEntity;
import com.gucheng.tcmmanager.repository.MedicineRepo;
import com.gucheng.tcmmanager.repository.PrescriptionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-09-20
 */
@Slf4j
@Component
public class PrescriptionDao {
    private final PrescriptionRepo prescriptionRepo;
    private final MedicineRepo medicineRepo;

    public PrescriptionDao(PrescriptionRepo prescriptionRepo, MedicineRepo medicineRepo) {
        this.prescriptionRepo = prescriptionRepo;
        this.medicineRepo = medicineRepo;
    }

    public void savePrescription(PrescriptionDTO prescriptionDTO) {
        PrescriptionEntity prescriptionEntity = new PrescriptionEntity();
        prescriptionEntity.setName(prescriptionDTO.getPrescriptionName());
        prescriptionEntity.setDescription(prescriptionDTO.getPrescriptionDesc());

        List<PrescriptionMdcEntity> prescriptionMdcEntityList = new ArrayList<>();
        prescriptionDTO.getPrescriptionMedicines().forEach(prescriptionMdc -> {
            System.out.println(prescriptionMdc);
            PrescriptionMdcEntity prescriptionMdcEntity = new PrescriptionMdcEntity();
            prescriptionMdcEntity.setMedicineEntity(medicineRepo.findByName(prescriptionMdc.getName()));
            prescriptionMdcEntity.setGram(prescriptionMdc.getGram());
            prescriptionMdcEntity.setWeight(prescriptionMdc.getWeight());
            prescriptionMdcEntity.setPrescriptionEntity(prescriptionEntity);
            prescriptionMdcEntityList.add(prescriptionMdcEntity);
        });

        prescriptionEntity.setMedicines(prescriptionMdcEntityList);
        prescriptionRepo.save(prescriptionEntity);
    }

    public List<PrescriptionEntity> findAll() {
        return prescriptionRepo.findAll();
    }

    public void deleteById(String id) {
        prescriptionRepo.deleteById(id);
    }
}
