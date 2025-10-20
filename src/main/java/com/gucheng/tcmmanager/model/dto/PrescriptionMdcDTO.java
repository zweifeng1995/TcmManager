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
    private double gram;
    private double weight;
}
