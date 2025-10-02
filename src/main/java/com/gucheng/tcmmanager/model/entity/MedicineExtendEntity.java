/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
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
@Table(name = "medicine_extend")
@NoArgsConstructor
@AllArgsConstructor
public class MedicineExtendEntity {
    @Id
    @Column(name = "id", nullable = false, unique = true, length = 16)
    private String id = UUID.randomUUID().toString().replace("-", "").substring(0, 16); // 唯一键

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id", nullable = false, unique = true)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MedicineEntity medicineEntity; // 外键，中药ID

    @Column(name = "nature_and_flavor", length = 64)
    private String natureAndFlavor; // 性味

    @Column(name = "channel_tropism", length = 64)
    private String channelTropism; // 归经

    @Column(name = "efficacy", length = 64)
    private String efficacy; // 功效

    @Column(name = "clinical_application", length = 64)
    private String clinicalApplication; // 临床应用

    @Column(name = "created_time", nullable = false)
    private Timestamp createdTime = new Timestamp(System.currentTimeMillis()); // 创建时间

    @Column(name = "updated_time", nullable = false)
    private Timestamp updatedTime = new Timestamp(System.currentTimeMillis()); // 更新时间
}
