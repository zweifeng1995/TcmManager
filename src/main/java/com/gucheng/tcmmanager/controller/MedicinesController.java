/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.controller;


import com.gucheng.tcmmanager.dao.MedicineDao;
import com.gucheng.tcmmanager.model.dto.MedicineDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-11-15
 */
@Slf4j
@RestController
public class MedicinesController {
    private final MedicineDao medicineDao;

    @Autowired
    public MedicinesController(MedicineDao medicineDao) {
        this.medicineDao = medicineDao;
    }

    @GetMapping("/medicine")
    public ResponseEntity<MedicineDTO> medicines(@RequestParam(value = "id") String id) {
        MedicineDTO medicineDTO = medicineDao.findById(id);
        return ResponseEntity.ok(medicineDTO);
    }

    @PutMapping("/medicines")
    public ResponseEntity<?> editMedicine(MedicineDTO medicineDTO) {
        log.info("medicineDTO: {}", medicineDTO);
        if (medicineDTO.getName() == null || medicineDTO.getName().isEmpty()) {
            throw new RuntimeException("药材名不能为空！");
        }
        if (medicineDTO.getMmpResearch() == null || medicineDTO.getMmpResearch().isEmpty()) {
            throw new RuntimeException("现代医学药理研究不能为空！");
        }
        if (medicineDTO.getProperties().stream().noneMatch(property -> property.getLevel() > 0)) {
            throw new RuntimeException("药性不能为空！");
        }
        if (medicineDTO.getFirstCategory() == null || medicineDTO.getFirstCategory().isEmpty()) {
            throw new RuntimeException("一级分类不能为空！");
        }
        if (medicineDTO.getSecondCategory() == null || medicineDTO.getSecondCategory().isEmpty()) {
            throw new RuntimeException("二级分类不能为空！");
        }
        if (medicineDTO.getWeight() == null || medicineDTO.getWeight().isNaN() || medicineDTO.getWeight().isInfinite() || medicineDTO.getWeight() < 0.0 || medicineDTO.getWeight() > 10.0) {
            throw new RuntimeException("权重为1-10之间的数！");
        }
        medicineDao.editMedicine(medicineDTO);
        return ResponseEntity.ok(medicineDTO);
    }
}
