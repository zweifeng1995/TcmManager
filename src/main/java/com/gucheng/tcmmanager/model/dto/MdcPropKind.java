/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.model.dto;


import lombok.Getter;

import java.util.Map;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-09-17
 */
@Getter
public enum MdcPropKind {
    COLD("cold", "寒"),
    HOT("hot", "热"),
    RISE("rise", "升"),
    FALL("fall", "降"),
    COLLECT("collect", "收"),
    DISPERSE("disperse", "散"),
    SUPPLEMENT("supplement", "补"),
    ELIMINATE("eliminate", "泻"),
    DRYNESS("dryness", "燥"),
    MOISTEN("moisten", "润");

    private final String valueEnUs;
    private final String valueZhCn;

    MdcPropKind(String valueEnUs, String valueZhCn) {
        this.valueEnUs = valueEnUs;
        this.valueZhCn = valueZhCn;
    }
}
