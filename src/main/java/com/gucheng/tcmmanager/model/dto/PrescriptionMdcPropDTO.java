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
    private Double level;

    public String getFormattedLevel() {
        return getFormattedValue(this.level);
    }

    private String getFormattedValue(Double value) {
        if (value == null) {
            return "";
        }

        if (value % 1 == 0) {
            return String.valueOf(value.intValue());
        } else {
            String formatted = String.format("%.3f", value);
            if (formatted.contains(".")) {
                formatted = formatted.replaceAll("0*$", "").replaceAll("\\.$", "");
            }
            return formatted;
        }
    }
}
