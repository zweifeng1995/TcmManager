/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmbrain.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.gucheng.tcmbrain.model.entity.MdcPropKind;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @JsonProperty("mmp_research")
    private String mmpResearch; // 现代医学药理研究

    @JsonProperty("properties")
    private List<MedicinePropDTO> properties = new ArrayList<>() {{ // 药性
        for (MdcPropKind value : MdcPropKind.values()) {
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
}
