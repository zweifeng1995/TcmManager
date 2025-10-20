/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-10-21
 */
@Data
@Builder
public class PrescriptionMdcPropDTO {
    @JsonProperty("kind")
    private MdcPropKind kind;

    @JsonProperty("level")
    private double level;
}
