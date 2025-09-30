/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmbrain.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 主程序入口
 *
 * @ author zhaoweifeng
 * @ since 2025-09-13
 */
@Getter
@Setter
@NoArgsConstructor
public class MedicineTableDTO {
    private int pageNum = 1;
    private int pageSize = 20;
    private int totalPage;
    private List<MedicineDTO> medicines;
}
