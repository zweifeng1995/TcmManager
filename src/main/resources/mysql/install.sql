CREATE TABLE IF NOT EXISTS medicine
(
    id           VARCHAR(16)                         NOT NULL UNIQUE,
    name         VARCHAR(32)                         NOT NULL UNIQUE,
    mmp_research VARCHAR(64)                         NOT NULL,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS medicine_extend
(
    id                   VARCHAR(16)                         NOT NULL UNIQUE,
    medicine_id          VARCHAR(16)                         NOT NULL UNIQUE,
    nature_and_flavor    VARCHAR(64),
    channel_tropism      VARCHAR(64),
    efficacy             VARCHAR(64),
    clinical_application VARCHAR(64),
    created_time         TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_time         TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (medicine_id) REFERENCES medicine (id)
);
CREATE TABLE IF NOT EXISTS medicine_property
(
    id           VARCHAR(16)                         NOT NULL UNIQUE,
    medicine_id  VARCHAR(16)                         NOT NULL,
    kind         INTEGER                             NOT NULL,
    level        INTEGER                             NOT NULL,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (medicine_id) REFERENCES medicine (id)
);
CREATE TABLE IF NOT EXISTS prescription
(
    id           VARCHAR(16)                         NOT NULL UNIQUE,
    name         VARCHAR(32)                         NOT NULL UNIQUE,
    description  VARCHAR(64),
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS prescription_medicine
(
    prescription_id VARCHAR(16)                         NOT NULL,
    medicine_id     VARCHAR(16)                         NOT NULL,
    created_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (prescription_id, medicine_id),
    FOREIGN KEY (prescription_id) REFERENCES prescription (id),
    FOREIGN KEY (medicine_id) REFERENCES medicine (id)
);
