/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmbrain.model.dto;


import com.gucheng.tcmbrain.model.entity.MedicinePropertyEntity;

import java.util.List;

/**
 * 药方中要展示的中药的关键字段
 *
 * @ author zhaoweifeng
 * @ since 2025-09-20
 */
public class PsctMdcInfoDTO {
    private String name; // 药名
    private String mmpResearch; // 现代医学药理研究
    private List<MedicinePropertyEntity> properties; // 药性
}
