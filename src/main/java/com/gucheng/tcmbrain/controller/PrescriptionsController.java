/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmbrain.controller;


import com.gucheng.tcmbrain.model.entity.MedicinePropertyEntity;
import com.gucheng.tcmbrain.model.entity.MedicineEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class PrescriptionsController {
    private static List<MedicineEntity> data = new ArrayList<>();

    static {
        for (int index = 0; index < 100; index++) {
            List<MedicinePropertyEntity> props = new ArrayList<>();
//            props.add(MedicinePropertyEntity.builder().kind(MdcPropKind.HOT).level(1).build());
//            props.add(MedicinePropertyEntity.builder().kind(MdcPropKind.ELIMINATE).level(1).build());
//            props.add(MedicinePropertyEntity.builder().kind(MdcPropKind.DISPERSE).level(1).build());
//            props.add(MedicinePropertyEntity.builder().kind(MdcPropKind.DRYNESS).level(1).build());
//            props.add(MedicinePropertyEntity.builder().kind(MdcPropKind.DRYNESS).level(1).build());
//            props.add(MedicinePropertyEntity.builder().kind(MdcPropKind.DRYNESS).level(1).build());
//            props.add(MedicinePropertyEntity.builder().kind(MdcPropKind.DRYNESS).level(1).build());
//            props.add(MedicinePropertyEntity.builder().kind(MdcPropKind.DRYNESS).level(1).build());
//            props.add(MedicinePropertyEntity.builder().kind(MdcPropKind.DRYNESS).level(1).build());
//            props.add(MedicinePropertyEntity.builder().kind(MdcPropKind.DRYNESS).level(1).build());
//            data.add(MedicineEntity.builder()
//                    .name("麻黄")
//                    .mmpResearch("解表药 - 发散风寒药")
//                    .natureAndFlavor("辛、微苦；温")
//                    .channelTropism("肺+膀胱")
//                    .efficacy("发汗解表，宣肺平喘，利水消肿")
//                    .clinicalApplication("1.用于风寒表实证。2.用于咳喘实证。3.用于风水水肿。")
//                    .properties(props).build());
        }
    }

    @GetMapping("/prescriptions")
    public String home(Model model,
                       @RequestParam(value = "xingwei", required = false, defaultValue = "all") String xingwei,
                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
//        int start = (pageNum - 1) * pageSize;
//        int end = Math.min(pageNum * pageSize, data.size());
//        MedicinePage medicinePage = new MedicinePage();
//        medicinePage.setPageNum(pageNum);
//        medicinePage.setPageSize(pageSize);
//        int lastPageDataNum = data.size() % medicinePage.getPageSize();
//        if (lastPageDataNum == 0) {
//            medicinePage.setTotalPage(data.size() / medicinePage.getPageSize());
//        } else {
//            medicinePage.setTotalPage((data.size() / medicinePage.getPageSize()) + 1);
//        }
//        medicinePage.setMedicines(data.subList(start, end));
//        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> xingwei:{}", xingwei);
//        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> pageNum:{}", pageNum);
//        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> pageSize:{}", pageSize);
//        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> totalPage:{}", medicinePage.getTotalPage());
//        model.addAttribute("medicinePage", medicinePage);
        return "prescriptions";
    }

    @GetMapping("/prescriptions/refreshTable")
    public String refreshTable(Model model,
                               @RequestParam(value = "xingwei", required = false, defaultValue = "all") String xingwei,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
//        int start = (pageNum - 1) * pageSize;
//        int end = Math.min(pageNum * pageSize, data.size());
//        MedicinePage medicinePage = new MedicinePage();
//        medicinePage.setPageNum(pageNum);
//        medicinePage.setPageSize(pageSize);
//        int lastPageDataNum = data.size() % medicinePage.getPageSize();
//        if (lastPageDataNum == 0) {
//            medicinePage.setTotalPage(data.size() / medicinePage.getPageSize());
//        } else {
//            medicinePage.setTotalPage((data.size() / medicinePage.getPageSize()) + 1);
//        }
//        medicinePage.setMedicines(data.subList(start, end));
//        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> xingwei:{}", xingwei);
//        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> pageNum:{}", pageNum);
//        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> pageSize:{}", pageSize);
//        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> totalPage:{}", medicinePage.getTotalPage());
//        model.addAttribute("medicinePage", medicinePage);
        return "prescriptions :: medicineTable";
    }
}
