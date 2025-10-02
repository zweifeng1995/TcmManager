/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PrescriptionTableDTO {
    private int pageNum = 1;
    private int pageSize = 20;
    private int totalPage = 1;
    private List<PrescriptionDTO> prescriptionList = new ArrayList<>();
}
