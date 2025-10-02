/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.controller;


import com.gucheng.tcmmanager.dao.MedicineDao;
import com.gucheng.tcmmanager.model.dto.*;
import com.gucheng.tcmmanager.model.entity.MedicineEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class WebMedicinesController {
    private final MedicineDao medicineDao;

    @Autowired
    public WebMedicinesController(MedicineDao medicineDao) {
        this.medicineDao = medicineDao;
    }

    @GetMapping("/medicines")
    public String medicines(Model model,
                            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
        List<MedicineEntity> medicineEntities = medicineDao.findAll();
        MedicineTableDTO medicineTableDTO = query(pageNum, pageSize, medicineEntities);
        model.addAttribute("medicineTableDTO", medicineTableDTO); // 用于展示
        model.addAttribute("prescriptionDTO", new PrescriptionDTO()); // 用于展示
        model.addAttribute("medicineDTO", new MedicineDTO()); // 用于添加
        return "medicines";
    }

    @PostMapping("/medicines")
    public String submitMedicine(Model model, @ModelAttribute MedicineDTO medicineDTO) {
        if (medicineDTO.getProperties().stream().noneMatch(property -> property.getLevel() > 0)) {
            throw new RuntimeException("药性不能为空！");
        }
        log.info("medicineDTO: {}", medicineDTO);
        medicineDao.saveMedicine(medicineDTO);
//        this.medicines(model, 1, 20);
//        model.addAttribute("submitErrorMsg", "提交失败！");
        return "medicines :: toastContainer";
    }

    @PutMapping("/medicines")
    public String editMedicine(Model model, @ModelAttribute MedicineDTO medicineDTO) {
        if (medicineDTO.getProperties().stream().noneMatch(property -> property.getLevel() > 0)) {
            throw new RuntimeException("药性不能为空！");
        }
        log.info("medicineDTO: {}", medicineDTO);
        medicineDao.editMedicine(medicineDTO);
//        this.medicines(model, 1, 20);
//        model.addAttribute("submitErrorMsg", "提交失败！");
        return "medicines :: toastContainer";
    }

    @DeleteMapping("/medicines")
    public String deleteMedicine(Model model, @RequestParam(value = "ids") String ids) {
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< deleteMedicine ids: {}.", ids);
        List<String> idList = List.of(ids.split(","));
        medicineDao.deleteMedicines(idList);
        return "medicines :: toastContainer";
    }

    @GetMapping("/refreshTable")
    public String refreshTable(Model model,
                               @RequestParam(value = "name", required = false, defaultValue = "") String name,
                               @RequestParam(value = "efficacy", required = false, defaultValue = "") String efficacy,
                               @RequestParam(value = "clinical_application", required = false, defaultValue = "") String clinicalApplication,
                               @RequestParam(value = "mdc_prop_kinds", required = false, defaultValue = "") String mdcPropKinds,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
        String[] mdcPropKindsArr = mdcPropKinds.split(",");
        List<Integer> mdcPropKindList = new ArrayList<>();
        try {
            for (String mdcPropKindStr : mdcPropKindsArr) {
                mdcPropKindList.add(MdcPropKind.valueOf(mdcPropKindStr).ordinal());
            }
            log.info("mdcPropKindList: {}", mdcPropKindList);
        } catch (Exception e) {

        }
        List<MedicineEntity> medicineEntities = medicineDao.findByCondition(name, efficacy, clinicalApplication, mdcPropKindList);

        MedicineTableDTO medicineTableDTO = query(pageNum, pageSize, medicineEntities);
        model.addAttribute("medicineTableDTO", medicineTableDTO); // 用于展示
        return "medicines :: medicineTable";
    }

    private MedicineTableDTO query(int pageNum, int pageSize, List<MedicineEntity> medicineEntities) {
        List<MedicineDTO> medicineDTOList = new ArrayList<>();
        medicineEntities.forEach(entity -> {
            MedicineDTO medicineDTO = new MedicineDTO();
            if (entity.getProperties() != null) {
                List<MedicinePropDTO> medicinePropDTOList = new ArrayList<>();
                entity.getProperties().forEach(property -> {
                    MedicinePropDTO medicinePropDTO = MedicinePropDTO.builder().kind(MdcPropKind.values()[property.getKind()]).level(property.getLevel()).build();
                    medicinePropDTOList.add(medicinePropDTO);
                });
                medicineDTO.setProperties(medicinePropDTOList);
            }
            medicineDTO.setId(entity.getId());
            medicineDTO.setName(entity.getName());
            medicineDTO.setMmpResearch(entity.getMmpResearch());
            medicineDTO.setNatureAndFlavor(entity.getMedicineExtendEntity().getNatureAndFlavor());
            medicineDTO.setChannelTropism(entity.getMedicineExtendEntity().getChannelTropism());
            medicineDTO.setEfficacy(entity.getMedicineExtendEntity().getEfficacy());
            medicineDTO.setClinicalApplication(entity.getMedicineExtendEntity().getClinicalApplication());
            medicineDTOList.add(medicineDTO);
        });
        MedicineTableDTO medicineTableDTO = new MedicineTableDTO();
        int start = (pageNum - 1) * pageSize;
        int end = Math.min(pageNum * pageSize, medicineDTOList.size());
        medicineTableDTO.setPageNum(pageNum);
        medicineTableDTO.setPageSize(pageSize);
        int lastPageDataNum = medicineDTOList.size() % medicineTableDTO.getPageSize();
        if (lastPageDataNum == 0) {
            medicineTableDTO.setTotalPage(medicineDTOList.size() / medicineTableDTO.getPageSize());
        } else {
            medicineTableDTO.setTotalPage((medicineDTOList.size() / medicineTableDTO.getPageSize()) + 1);
        }
        medicineTableDTO.setMedicines(medicineDTOList.subList(start, end));
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> pageNum:{}", pageNum);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> pageSize:{}", pageSize);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> totalPage:{}", medicineTableDTO.getTotalPage());
        return medicineTableDTO;
    }
}
