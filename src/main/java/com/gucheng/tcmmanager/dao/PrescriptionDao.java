/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.dao;


import com.gucheng.tcmmanager.model.dto.PrescriptionDTO;
import com.gucheng.tcmmanager.model.entity.MedicineEntity;
import com.gucheng.tcmmanager.model.entity.PrescriptionEntity;
import com.gucheng.tcmmanager.repository.MedicineRepo;
import com.gucheng.tcmmanager.repository.PrescriptionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
        List<MedicineEntity> medicineEntities = prescriptionDTO.getPrescriptionMedicinesName().stream().map(medicineRepo::findByName).collect(Collectors.toList());
        PrescriptionEntity prescriptionEntity = new PrescriptionEntity(prescriptionDTO, medicineEntities);
        prescriptionRepo.save(prescriptionEntity);
    }

    public List<PrescriptionEntity> findAll() {
        return prescriptionRepo.findAll();
    }

    public void deleteById(String id) {
        prescriptionRepo.deleteById(id);
    }
}
