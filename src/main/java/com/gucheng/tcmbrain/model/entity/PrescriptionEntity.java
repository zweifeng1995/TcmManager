/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmbrain.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-09-20
 */
@Data
@Entity
@Builder
@Table(name = "prescription")
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionEntity {
    @Id
    @Column(name = "id", nullable = false, unique = true, length = 16)
    private String id = UUID.randomUUID().toString().replace("-", "").substring(0, 16); // 唯一键

    @Column(name = "name", nullable = false, unique = true, length = 32)
    private String name; // 药方名

    @Column(name = "description", nullable = false, length = 64)
    private String description; // 药方描述

    @Column(name = "created_time", nullable = false)
    private Timestamp createdTime = Timestamp.from(Instant.ofEpochSecond(System.currentTimeMillis())); // 创建时间

    @Column(name = "updated_time", nullable = false)
    private Timestamp updatedTime = Timestamp.from(Instant.ofEpochSecond(System.currentTimeMillis()));; // 更新时间

    @ManyToMany(targetEntity = MedicineEntity.class)
    @JoinTable(name = "prescription_medicine",
            joinColumns = {@JoinColumn(name = "prescription_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "medicine_id", referencedColumnName = "id")})
    private List<MedicineEntity> medicines; // 中药信息
}
