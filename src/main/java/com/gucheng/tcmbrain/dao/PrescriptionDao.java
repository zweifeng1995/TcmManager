/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmbrain.dao;


import com.gucheng.tcmbrain.model.dto.PrescriptionDTO;
import com.gucheng.tcmbrain.model.entity.PrescriptionEntity;
import com.gucheng.tcmbrain.repository.PrescriptionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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

    public PrescriptionDao(PrescriptionRepo prescriptionRepo) {
        this.prescriptionRepo = prescriptionRepo;
    }

    public void savePrescription(PrescriptionDTO prescriptionDTO) {

        prescriptionRepo.save(null);
    }

    public List<PrescriptionEntity> findAll() {
        return prescriptionRepo.findAll();
    }
}
