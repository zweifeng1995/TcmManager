/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 主程序入口
 *
 * @ author zhaoweifeng
 * @ since 2025-09-13
 */
@Data
@NoArgsConstructor
public class MedicineTableDTO {
    private int pageNum = 1;
    private int pageSize = 20;
    private int totalPage;
    private List<MedicineDTO> medicines;
}
