/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-10-21
 */
@Data
@Entity
@Table(name = "prescription_medicine")
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionMdcEntity {
    @Id
    @Column(name = "id", nullable = false, unique = true, length = 16)
    private String id = UUID.randomUUID().toString().replace("-", "").substring(0, 16); // 唯一键

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MedicineEntity medicineEntity; // 外键，中药ID

    @Column(name = "gram", nullable = false)
    private double gram; // 克数

    @Column(name = "weight", nullable = false)
    private double weight; // 权重

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescription_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PrescriptionEntity prescriptionEntity; // 外键，药方ID
}
