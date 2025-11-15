/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.gucheng.tcmmanager.model.entity.MedicineEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-09-21
 */
@Data
@NoArgsConstructor
public class MedicineDTO {
    @JsonProperty("id")
    private String id; // ID

    @JsonProperty("name")
    private String name; // 药名

    @JsonProperty("weight")
    private Double weight = 1.0; // 药名

    @JsonProperty("first_category")
    private String firstCategory; // 一级分类

    @JsonProperty("second_category")
    private String secondCategory; // 二级分类

    @JsonProperty("mmp_research")
    private String mmpResearch; // 现代医学药理研究

    @JsonProperty("properties")
    private List<MedicinePropDTO> properties = new ArrayList<>() {{ // 药性
        for (MdcPropKind value : MdcPropKind.valuesBySort()) {
            add(MedicinePropDTO.builder().kind(value).level(0).build());
        }
    }};

    @JsonProperty("nature_and_flavor")
    private String natureAndFlavor; // 性味

    @JsonProperty("channel_tropism")
    private String channelTropism; // 归经

    @JsonProperty("efficacy")
    private String efficacy; // 功效

    @JsonProperty("clinical_application")
    private String clinicalApplication; // 临床应用

    public MedicineDTO(MedicineEntity medicineEntity) {
        this.id = medicineEntity.getId();
        this.name = medicineEntity.getName();
        this.weight = medicineEntity.getWeight();
        this.firstCategory = medicineEntity.getFirstCategory();
        this.secondCategory = medicineEntity.getSecondCategory();
        this.mmpResearch = medicineEntity.getMmpResearch();
        this.natureAndFlavor = medicineEntity.getMedicineExtendEntity().getNatureAndFlavor();
        this.channelTropism = medicineEntity.getMedicineExtendEntity().getChannelTropism();
        this.efficacy = medicineEntity.getMedicineExtendEntity().getEfficacy();
        this.clinicalApplication = medicineEntity.getMedicineExtendEntity().getClinicalApplication();

        List<MedicinePropDTO> properties = new ArrayList<>();
        medicineEntity.getProperties().forEach(prop -> {
            MedicinePropDTO.MedicinePropDTOBuilder propDTOBuilder = MedicinePropDTO.builder();
            for (MdcPropKind value : MdcPropKind.valuesBySort()) {
                if (value.ordinal() == prop.getKind()) {
                    propDTOBuilder.kind(value);
                    break;
                }
            }
            MedicinePropDTO propDTO = propDTOBuilder.level(prop.getLevel()).build();
            properties.add(propDTO);
        });
        this.properties = properties;
    }
}
