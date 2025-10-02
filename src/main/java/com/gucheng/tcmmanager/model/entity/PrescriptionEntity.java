/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gucheng.tcmmanager.model.dto.PrescriptionDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "prescription")
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionEntity {
    @Id
    @Column(name = "id", nullable = false, unique = true, length = 16)
    private String id = UUID.randomUUID().toString().replace("-", "").substring(0, 16); // 唯一键

    @Column(name = "name", nullable = false, unique = true, length = 32)
    private String name; // 药方名

    @Column(name = "description", length = 64)
    private String description; // 药方描述

    @Column(name = "created_time", nullable = false)
    private Timestamp createdTime = new Timestamp(System.currentTimeMillis()); // 创建时间

    @Column(name = "updated_time", nullable = false)
    private Timestamp updatedTime = new Timestamp(System.currentTimeMillis()); // 更新时间

    @ManyToMany
    @JoinTable(
            name = "prescription_medicine", // 中间表名称
            joinColumns = @JoinColumn(name = "prescription_id"), // 当前实体在中间表的外键
            inverseJoinColumns = @JoinColumn(name = "medicine_id") // 关联实体在中间表的外键
    )
    private List<MedicineEntity> medicines; // 中药信息

    public PrescriptionEntity(PrescriptionDTO prescriptionDTO, List<MedicineEntity> medicineEntities) {
        this.name = prescriptionDTO.getPrescriptionName();
        this.description = prescriptionDTO.getPrescriptionDesc();
        this.medicines = medicineEntities;
    }
}
