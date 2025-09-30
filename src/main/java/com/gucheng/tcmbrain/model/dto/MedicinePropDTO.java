/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmbrain.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.gucheng.tcmbrain.model.entity.MdcPropKind;
import lombok.Builder;
import lombok.Data;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-09-21
 */
@Data
@Builder
public class MedicinePropDTO {
    @JsonProperty("kind")
    private MdcPropKind kind;

    @JsonProperty("level")
    private int level;
}
