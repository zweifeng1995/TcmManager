/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-09-21
 */
@Data
@NoArgsConstructor
public class PrescriptionDTO {
    @JsonProperty("prescription_id")
    private String prescriptionId; // ID

    @JsonProperty("prescription_name")
    private String prescriptionName; // 药方名

    @JsonProperty("prescription_desc")
    private String prescriptionDesc; // 药方描述

    @JsonProperty("prescription_medicines")
    private List<PrescriptionMdcDTO> prescriptionMedicines; // 药材列表

    @JsonProperty("prescription_mdc_prop_sum")
    private List<PrescriptionMdcPropDTO> prescriptionMdcPropSum; // 药性总和

    @JsonProperty("prescription_created_time")
    private String prescriptionCreatedTime; // 药方创建时间

    @JsonProperty("prescription_updated_time")
    private String prescriptionUpdatedTime; // 药方更新时间
}
