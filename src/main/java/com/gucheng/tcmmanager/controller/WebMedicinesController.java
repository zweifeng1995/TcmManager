/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.controller;


import com.gucheng.tcmmanager.config.MedicineCategoryConfig;
import com.gucheng.tcmmanager.dao.MedicineDao;
import com.gucheng.tcmmanager.model.dto.*;
import com.gucheng.tcmmanager.model.entity.MedicineEntity;
import com.gucheng.tcmmanager.model.entity.MedicinePropertyEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@Controller
public class WebMedicinesController {
    private final MedicineDao medicineDao;
    private final MedicineCategoryConfig medicineCategoryConfig;

    @Autowired
    public WebMedicinesController(MedicineDao medicineDao, MedicineCategoryConfig medicineCategoryConfig) {
        this.medicineDao = medicineDao;
        this.medicineCategoryConfig = medicineCategoryConfig;
    }

    @GetMapping("/medicines")
    public String medicines(Model model,
                            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
        List<MedicineEntity> medicineEntities = medicineDao.findAll();
        MedicineTableDTO medicineTableDTO = query(pageNum, pageSize, medicineEntities);

        Map<String, String> medicinePropertyKindMap = new LinkedHashMap<>();
        Arrays.stream(MdcPropKind.valuesBySort()).forEach(value -> medicinePropertyKindMap.put(value.name(), value.getValueZhCn()));
        model.addAttribute("medicineTableDTO", medicineTableDTO); // 用于前台药材列表展示
        model.addAttribute("submitMedicineDTO", new MedicineDTO()); // 用于前台添加药材
        model.addAttribute("editMedicineDTO", new MedicineDTO()); // 用于前台修改药材
        model.addAttribute("prescriptionDTO", new PrescriptionDTO()); // 用于前台添加药方
        model.addAttribute("mdcCategories", medicineCategoryConfig.getCategoriesMap()); // 用于前台获取一二级分类下拉框数据，格式：{"一级分类": ["二级分类"]}
        model.addAttribute("mdcPropKindMap", medicinePropertyKindMap); // 用于药性计算时知道计算哪些药性，格式：{"HOT": "热"}
        return "medicines";
    }

    @PostMapping("/medicines")
    public String submitMedicine(Model model, @ModelAttribute MedicineDTO medicineDTO) {
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
        log.info("medicineDTO: {}", medicineDTO);
        medicineDao.saveMedicine(medicineDTO);
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

    @GetMapping("/refreshMedicinesTable")
    public String refreshTable(Model model,
                               @RequestParam(value = "all", required = false, defaultValue = "") String all,
                               @RequestParam(value = "first_category", required = false, defaultValue = "") String firstCategory,
                               @RequestParam(value = "second_category", required = false, defaultValue = "") String secondCategory,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {

        List<MedicineEntity> medicineEntities = medicineDao.findByCondition(all, firstCategory, secondCategory);

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
                for (MdcPropKind mdcPropKind : MdcPropKind.valuesBySort()) {
                    Optional<MedicinePropertyEntity> medicinePropertyEntity = entity.getProperties().stream()
                            .filter(property -> property.getKind() == mdcPropKind.ordinal())
                            .findFirst();
                    medicinePropertyEntity.ifPresent(propertyEntity -> medicinePropDTOList.add(MedicinePropDTO.builder().kind(mdcPropKind).level(propertyEntity.getLevel()).build()));
                }
                medicineDTO.setProperties(medicinePropDTOList);
            }
            medicineDTO.setId(entity.getId());
            medicineDTO.setName(entity.getName());
            medicineDTO.setWeight(entity.getWeight());
            medicineDTO.setFirstCategory(entity.getFirstCategory());
            medicineDTO.setSecondCategory(entity.getSecondCategory());
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
