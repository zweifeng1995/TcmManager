/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.controller;


import com.gucheng.tcmmanager.dao.PrescriptionDao;
import com.gucheng.tcmmanager.model.dto.MdcPropKind;
import com.gucheng.tcmmanager.model.dto.MedicinePropDTO;
import com.gucheng.tcmmanager.model.dto.PrescriptionDTO;
import com.gucheng.tcmmanager.model.dto.PrescriptionTableDTO;
import com.gucheng.tcmmanager.model.entity.MedicineEntity;
import com.gucheng.tcmmanager.model.entity.MedicinePropertyEntity;
import com.gucheng.tcmmanager.model.entity.PrescriptionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class WebPrescriptionsController {
    private final PrescriptionDao prescriptionDao;

    @Autowired
    public WebPrescriptionsController(PrescriptionDao prescriptionDao) {
        this.prescriptionDao = prescriptionDao;
    }

    @GetMapping("/prescriptions")
    public String prescriptions(Model model,
                                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
//        model.addAttribute("prescriptionTableDTO", prescriptionTableDTO); // 用于展示
        List<PrescriptionEntity> prescriptionEntities = prescriptionDao.findAll();
        PrescriptionTableDTO prescriptionTableDTO = new PrescriptionTableDTO();

        if (prescriptionEntities == null || prescriptionEntities.isEmpty()) {
            model.addAttribute("prescriptionTableDTO", prescriptionTableDTO);
            return "prescriptions";
        }

        int start = (pageNum - 1) * pageSize;
        int end = Math.min(pageNum * pageSize, prescriptionEntities.size());
        prescriptionTableDTO.setPageNum(pageNum);
        prescriptionTableDTO.setPageSize(pageSize);
        int lastPageDataNum = prescriptionEntities.size() % prescriptionTableDTO.getPageSize();
        if (lastPageDataNum == 0) {
            prescriptionTableDTO.setTotalPage(prescriptionEntities.size() / prescriptionTableDTO.getPageSize());
        } else {
            prescriptionTableDTO.setTotalPage((prescriptionEntities.size() / prescriptionTableDTO.getPageSize()) + 1);
        }
        prescriptionEntities = prescriptionEntities.subList(start, end);

        prescriptionEntities.forEach(prescription -> {
            PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
            prescriptionDTO.setPrescriptionId(prescription.getId());
            prescriptionDTO.setPrescriptionName(prescription.getName());
            prescriptionDTO.setPrescriptionDesc(prescription.getDescription());
            prescriptionDTO.setPrescriptionMedicinesName(prescription.getMedicines().stream().map(MedicineEntity::getName).collect(Collectors.toList()));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            prescriptionDTO.setPrescriptionUpdatedTime(formatter.format(new Date(prescription.getUpdatedTime().getTime())));

            List<MedicinePropDTO> medicinePropDTOList = new ArrayList<>();
            for (MdcPropKind value : MdcPropKind.values()) {
                AtomicInteger sum = new AtomicInteger();
                prescription.getMedicines().forEach(medicine -> {
                    for (MedicinePropertyEntity property : medicine.getProperties()) {
                        if (property.getKind().equals(value.ordinal())) {
                            sum.addAndGet(property.getLevel());
                        }
                    }
                });
                medicinePropDTOList.add(MedicinePropDTO.builder().kind(value).level(sum.get()).build());
            }
            prescriptionDTO.setPrescriptionMdcPropSum(medicinePropDTOList);
            prescriptionTableDTO.getPrescriptionList().add(prescriptionDTO);
        });

        log.info("prescriptionTableDTO:{}", prescriptionTableDTO);
        model.addAttribute("prescriptionTableDTO", prescriptionTableDTO);
        return "prescriptions";
    }

    @PostMapping("/prescriptions")
    public String submitPrescriptions(Model model, @ModelAttribute PrescriptionDTO prescriptionDTO) {
        log.info("prescriptionDTO: {}", prescriptionDTO);
        prescriptionDao.savePrescription(prescriptionDTO);
        return "medicines :: toastContainer";
    }

    @DeleteMapping("/prescriptions")
    public String deletePrescriptions(Model model, @RequestParam(value = "id") String id) {
        log.info("id: {}", id);
        prescriptionDao.deleteById(id);
        return "medicines :: toastContainer";
    }

    @GetMapping("/prescriptions/refresh")
    public String refreshPrescriptions(Model model,
                                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
//        model.addAttribute("prescriptionTableDTO", prescriptionTableDTO); // 用于展示
        List<PrescriptionEntity> prescriptionEntities = prescriptionDao.findAll();
        PrescriptionTableDTO prescriptionTableDTO = new PrescriptionTableDTO();

        if (prescriptionEntities == null || prescriptionEntities.isEmpty()) {
            model.addAttribute("prescriptionTableDTO", prescriptionTableDTO);
            return "prescriptions";
        }

        int start = (pageNum - 1) * pageSize;
        int end = Math.min(pageNum * pageSize, prescriptionEntities.size());
        prescriptionTableDTO.setPageNum(pageNum);
        prescriptionTableDTO.setPageSize(pageSize);
        int lastPageDataNum = prescriptionEntities.size() % prescriptionTableDTO.getPageSize();
        if (lastPageDataNum == 0) {
            prescriptionTableDTO.setTotalPage(prescriptionEntities.size() / prescriptionTableDTO.getPageSize());
        } else {
            prescriptionTableDTO.setTotalPage((prescriptionEntities.size() / prescriptionTableDTO.getPageSize()) + 1);
        }
        prescriptionEntities = prescriptionEntities.subList(start, end);

        prescriptionEntities.forEach(prescription -> {
            PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
            prescriptionDTO.setPrescriptionId(prescription.getId());
            prescriptionDTO.setPrescriptionName(prescription.getName());
            prescriptionDTO.setPrescriptionDesc(prescription.getDescription());
            prescriptionDTO.setPrescriptionMedicinesName(prescription.getMedicines().stream().map(MedicineEntity::getName).collect(Collectors.toList()));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            prescriptionDTO.setPrescriptionUpdatedTime(formatter.format(new Date(prescription.getUpdatedTime().getTime())));

            List<MedicinePropDTO> medicinePropDTOList = new ArrayList<>();
            for (MdcPropKind value : MdcPropKind.values()) {
                AtomicInteger sum = new AtomicInteger();
                prescription.getMedicines().forEach(medicine -> {
                    for (MedicinePropertyEntity property : medicine.getProperties()) {
                        if (property.getKind().equals(value.ordinal())) {
                            sum.addAndGet(property.getLevel());
                        }
                    }
                });
                medicinePropDTOList.add(MedicinePropDTO.builder().kind(value).level(sum.get()).build());
            }
            prescriptionDTO.setPrescriptionMdcPropSum(medicinePropDTOList);
            prescriptionTableDTO.getPrescriptionList().add(prescriptionDTO);
        });

        log.info("prescriptionTableDTO:{}", prescriptionTableDTO);
        model.addAttribute("prescriptionTableDTO", prescriptionTableDTO);
        return "prescriptions :: prescriptionsTable";
    }
}
