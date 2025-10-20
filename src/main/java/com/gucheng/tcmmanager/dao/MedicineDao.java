/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.dao;


import com.gucheng.tcmmanager.model.dto.MedicineDTO;
import com.gucheng.tcmmanager.model.dto.MedicinePropDTO;
import com.gucheng.tcmmanager.model.entity.MedicineEntity;
import com.gucheng.tcmmanager.model.entity.MedicineExtendEntity;
import com.gucheng.tcmmanager.model.entity.MedicinePropertyEntity;
import com.gucheng.tcmmanager.repository.MedicineExtendRepo;
import com.gucheng.tcmmanager.repository.MedicinePropertyRepo;
import com.gucheng.tcmmanager.repository.MedicineRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-09-20
 */
@Slf4j
@Component
public class MedicineDao {
    private final MedicineRepo medicineRepo;
    private final MedicineExtendRepo medicineExtendRepo;
    private final MedicinePropertyRepo medicinePropertyRepo;

    @Autowired
    public MedicineDao(MedicineRepo medicineRepo, MedicineExtendRepo medicineExtendRepo, MedicinePropertyRepo medicinePropertyRepo) {
        this.medicineRepo = medicineRepo;
        this.medicineExtendRepo = medicineExtendRepo;
        this.medicinePropertyRepo = medicinePropertyRepo;
    }

    @Transactional
    public void saveMedicine(MedicineDTO medicineDTO) {
        MedicineEntity medicineEntity = new MedicineEntity();

        List<MedicinePropertyEntity> medicinePropertyEntities = new ArrayList<>();
        if (medicineDTO.getProperties() != null) {
            for (MedicinePropDTO propertyDTO : medicineDTO.getProperties()) {
                if (propertyDTO.getLevel() <= 0) {
                    continue;
                }
                MedicinePropertyEntity medicinePropertyEntity = new MedicinePropertyEntity();
                medicinePropertyEntity.setKind(propertyDTO.getKind().ordinal());
                medicinePropertyEntity.setLevel(propertyDTO.getLevel());
                medicinePropertyEntity.setMedicineEntity(medicineEntity);
                medicinePropertyEntities.add(medicinePropertyEntity);
            }
        }

        MedicineExtendEntity medicineExtendEntity = new MedicineExtendEntity();
        medicineExtendEntity.setNatureAndFlavor(medicineDTO.getNatureAndFlavor());
        medicineExtendEntity.setEfficacy(medicineDTO.getEfficacy());
        medicineExtendEntity.setChannelTropism(medicineDTO.getChannelTropism());
        medicineExtendEntity.setClinicalApplication(medicineDTO.getClinicalApplication());
        medicineExtendEntity.setMedicineEntity(medicineEntity);

        medicineEntity.setName(medicineDTO.getName());
        medicineEntity.setMmpResearch(medicineDTO.getMmpResearch());
        medicineEntity.setFirstCategory(medicineDTO.getFirstCategory());
        medicineEntity.setSecondCategory(medicineDTO.getSecondCategory());
        medicineEntity.setMedicineExtendEntity(medicineExtendEntity);
        if (!medicinePropertyEntities.isEmpty()) {
            medicineEntity.setProperties(medicinePropertyEntities);
        }

        medicineRepo.save(medicineEntity);
    }

    @Transactional
    public void editMedicine(MedicineDTO medicineDTO) {
        MedicineEntity medicineEntity = medicineRepo.findByName((medicineDTO.getName()));
        medicineEntity.setMmpResearch(medicineDTO.getMmpResearch());
        medicineEntity.setFirstCategory(medicineDTO.getFirstCategory());
        medicineEntity.setSecondCategory(medicineDTO.getSecondCategory());

        medicineExtendRepo.findByMedicineEntityId(medicineEntity.getId()).ifPresent(extend -> {
            extend.setNatureAndFlavor(medicineDTO.getNatureAndFlavor());
            extend.setChannelTropism(medicineDTO.getChannelTropism());
            extend.setEfficacy(medicineDTO.getEfficacy());
            extend.setClinicalApplication(medicineDTO.getClinicalApplication());
            extend.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
            // 不需要调用save，@Transactional 提交时会自动更新
        });

        medicinePropertyRepo.deleteByMedicineEntityId(medicineEntity.getId());

        if (medicineDTO.getProperties() != null) {
            List<MedicinePropertyEntity> properties = medicineDTO.getProperties().stream()
                    .filter(propertyDTO -> propertyDTO.getLevel() > 0)
                    .map(property -> {
                        MedicinePropertyEntity medicinePropertyEntity = new MedicinePropertyEntity();
                        medicinePropertyEntity.setMedicineEntity(medicineEntity);
                        medicinePropertyEntity.setKind(property.getKind().ordinal());
                        medicinePropertyEntity.setLevel(property.getLevel());
                        return medicinePropertyEntity;
                    })
                    .collect(Collectors.toList());

            medicinePropertyRepo.saveAll(properties);
        }
    }

    @Transactional
    public void deleteMedicines(List<String> idList) {
        if (idList == null || idList.isEmpty()) {
            log.warn("The id list is empty.");
            return;
        }
        List<MedicineEntity> medicines = medicineRepo.findAllById(idList);
        medicineRepo.deleteAll(medicines);
    }

    public List<MedicineEntity> findAll() {
        return medicineRepo.findAll();
    }

    public List<MedicineEntity> findByCondition(String name, String efficacy, String clinicalApplication,String firstCategory, String secondCategory, String natureAndFlavor, String channelTropism) {
        return medicineRepo.findByCondition(name, efficacy, clinicalApplication,firstCategory, secondCategory, natureAndFlavor, channelTropism);
    }
}
