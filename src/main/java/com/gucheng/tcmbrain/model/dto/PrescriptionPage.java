/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmbrain.model.dto;


import com.gucheng.tcmbrain.model.entity.MedicinePropertyEntity;

import java.util.List;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-09-20
 */
public class PrescriptionPage {
    private int pageNum;
    private int pageSize;
    private int totalPage;
    private String id; // 唯一键
    private String name; // 药方名
    private String description; // 药方描述
    private List<PsctMdcInfoDTO>  medicinesInfo; // 中药信息
    private List<MedicinePropertyEntity> propertiesSum; // 药性总和
}
