/* Copyright (c) 2025 GuCheng Technologies Co., Ltd. All Rights Reserved. */

package com.gucheng.tcmmanager.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能说明
 *
 * @ author zhaoweifeng
 * @ since 2025-09-21
 */
@Getter
@Configuration
@ConfigurationProperties(prefix = "medicine")
@PropertySource(value = "classpath:medicine-categories.yml", factory = YamlPropertySourceFactory.class)
public class MedicineCategoryConfig {
    private final List<Category> categories = new ArrayList<>();

    @Setter
    @Getter
    @ToString
    public static class Category {
        private String key;
        private final List<String> items = new ArrayList<>();
    }

    public Map<String, List<String>> getCategoriesMap() {
        return toMap();
    }

    private Map<String, List<String>> toMap() {
        return categories.stream().collect(Collectors.toMap(Category::getKey, Category::getItems, (v1, v2) -> v1, LinkedHashMap::new));
    }
}
