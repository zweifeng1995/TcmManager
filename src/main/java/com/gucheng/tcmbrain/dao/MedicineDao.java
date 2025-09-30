/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmbrain.dao;


import com.gucheng.tcmbrain.model.dto.MedicineDTO;
import com.gucheng.tcmbrain.model.dto.MedicinePropDTO;
import com.gucheng.tcmbrain.model.entity.MedicineEntity;
import com.gucheng.tcmbrain.model.entity.MedicineExtendEntity;
import com.gucheng.tcmbrain.model.entity.MedicinePropertyEntity;
import com.gucheng.tcmbrain.repository.MedicineRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    public MedicineDao(MedicineRepo medicineRepo) {
        this.medicineRepo = medicineRepo;
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
        medicineEntity.setMedicineExtendEntity(medicineExtendEntity);
        if (!medicinePropertyEntities.isEmpty()) {
            medicineEntity.setProperties(medicinePropertyEntities);
        }

        medicineRepo.save(medicineEntity);
    }

    public List<MedicineEntity> findAll() {
        return medicineRepo.findAll();
    }

    public List<MedicineEntity> findByCondition(String name, String efficacy, String clinicalApplication, List<Integer> mdcPropKindList) {
        return medicineRepo.findByCondition(name, efficacy, clinicalApplication, mdcPropKindList);
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
}
