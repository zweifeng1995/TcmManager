/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmbrain.TCMBrain.dao;


import com.gucheng.tcmbrain.model.entity.MdcPropKind;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-09-20
 */
@Slf4j
public class MedicineDaoTest {
    @Test
    public void test() {
        log.info("test:{}", Arrays.stream(MdcPropKind.values()).collect(Collectors.toList()));
    }
}
