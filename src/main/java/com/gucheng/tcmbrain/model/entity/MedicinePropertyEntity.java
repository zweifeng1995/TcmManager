/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmbrain.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-09-17
 */
@Data
@Entity
@Builder
@Table(name = "medicine_property")
@NoArgsConstructor
@AllArgsConstructor
public class MedicinePropertyEntity {
    @Id
    @Column(name = "id", nullable = false, unique = true, length = 16)
    private String id = UUID.randomUUID().toString().replace("-", "").substring(0, 16); // 唯一键

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MedicineEntity medicineEntity; // 外键，中药ID

    @Column(name = "kind", nullable = false)
    private Integer kind;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "created_time", nullable = false)
    private Timestamp createdTime = new Timestamp(System.currentTimeMillis()); // 创建时间

    @Column(name = "updated_time", nullable = false)
    private Timestamp updatedTime = new Timestamp(System.currentTimeMillis());; // 更新时间
}
