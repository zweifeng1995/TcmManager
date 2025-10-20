CREATE TABLE IF NOT EXISTS medicine
(
    id              VARCHAR(16)                         NOT NULL UNIQUE, -- 药材ID
    name            VARCHAR(32)                         NOT NULL UNIQUE, -- 药材名称
    first_category  VARCHAR(16),                                         -- 一级分类
    second_category VARCHAR(16),                                         -- 二级分类
    mmp_research    VARCHAR(64)                         NOT NULL,        -- 现代医学药理研究
    created_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,        -- 创建时间
    updated_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,        -- 更新时间
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS medicine_extend
(
    id                   VARCHAR(16)                         NOT NULL UNIQUE, -- 主键
    medicine_id          VARCHAR(16)                         NOT NULL UNIQUE, -- 药材ID
    nature_and_flavor    VARCHAR(64),                                         -- 性味
    channel_tropism      VARCHAR(64),                                         -- 归经
    efficacy             VARCHAR(64),                                         -- 功效
    clinical_application VARCHAR(64),                                         -- 临床应用
    created_time         TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,        -- 创建时间
    updated_time         TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,        -- 更新时间
    PRIMARY KEY (id),
    FOREIGN KEY (medicine_id) REFERENCES medicine (id)
);
CREATE TABLE IF NOT EXISTS medicine_property
(
    id           VARCHAR(16)                         NOT NULL UNIQUE, -- 主键
    medicine_id  VARCHAR(16)                         NOT NULL,        -- 药材ID
    kind         INTEGER                             NOT NULL,        -- 药性
    level        INTEGER                             NOT NULL,        -- 药性等级
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,        -- 创建时间
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,        -- 更新时间
    PRIMARY KEY (id),
    FOREIGN KEY (medicine_id) REFERENCES medicine (id)
);
CREATE TABLE IF NOT EXISTS prescription
(
    id           VARCHAR(16)                         NOT NULL UNIQUE, -- 药方ID
    name         VARCHAR(32)                         NOT NULL UNIQUE, -- 药方名
    description  VARCHAR(64),                                         -- 药方描述
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,        -- 创建时间
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,        -- 更新时间
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS prescription_medicine
(
    id              VARCHAR(16)                         NOT NULL UNIQUE, -- 主键
    prescription_id VARCHAR(16)                         NOT NULL, -- 药方ID
    medicine_id     VARCHAR(16)                         NOT NULL, -- 药材ID
    gram            DOUBLE                              NOT NULL, -- 药材克数
    weight          DOUBLE                              NOT NULL, -- 药材权重
    created_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, -- 创建时间
    updated_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, -- 更新时间
    PRIMARY KEY (id),
    FOREIGN KEY (prescription_id) REFERENCES prescription (id),
    FOREIGN KEY (medicine_id) REFERENCES medicine (id)
);
