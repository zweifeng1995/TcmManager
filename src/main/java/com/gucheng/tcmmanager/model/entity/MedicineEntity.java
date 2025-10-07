/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * 主程序入口
 *
 * @ author zhaoweifeng
 * @ since 2025-09-13
 */
@Data
@Entity
@Table(name = "medicine")
@NoArgsConstructor
@AllArgsConstructor
public class MedicineEntity {
    @Id
    @Column(name = "id", nullable = false, unique = true, length = 16)
    private String id = UUID.randomUUID().toString().replace("-", "").substring(0, 16); // 唯一键

    @Column(name = "name", nullable = false, unique = true, length = 32)
    private String name; // 药名

    @Column(name = "first_category", nullable = false, length = 16)
    private String firstCategory; // 一级分类

    @Column(name = "second_category", nullable = false, length = 16)
    private String secondCategory; // 二级分类

    @Column(name = "mmp_research", nullable = false, length = 64)
    private String mmpResearch; // 现代医学药理研究

    @Column(name = "created_time", nullable = false)
    private Timestamp createdTime = new Timestamp(System.currentTimeMillis()); // 创建时间

    @Column(name = "updated_time", nullable = false)
    private Timestamp updatedTime = new Timestamp(System.currentTimeMillis()); // 更新时间

    @OneToMany(mappedBy = "medicineEntity",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicinePropertyEntity> properties; // 药性

    @OneToOne(mappedBy = "medicineEntity",cascade = CascadeType.ALL, orphanRemoval = true)
    private MedicineExtendEntity medicineExtendEntity; // 中药额外信息：性味、归经、功效、临床应用等

    @ManyToMany(mappedBy = "medicines")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<PrescriptionEntity> prescriptions;
}
