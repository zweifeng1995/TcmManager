/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.model.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-10-21
 */
@Data
@NoArgsConstructor
public class PrescriptionMdcDTO {
    private String name;
    private Double gram;
    private Double weight;

    public String getFormattedGram() {
        return getFormattedValue(this.gram);
    }

    public String getFormattedWeight() {
        return getFormattedValue(this.weight);
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
