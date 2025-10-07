ALTER TABLE medicine
    ADD COLUMN first_category VARCHAR(16) AFTER name;
ALTER TABLE medicine
    ADD COLUMN second_category VARCHAR(16) AFTER first_category;

UPDATE medicine
SET first_category = '其他';
UPDATE medicine
SET second_category = '其他';

ALTER TABLE medicine
    MODIFY first_category VARCHAR(16) NOT NULL;
ALTER TABLE medicine
    MODIFY second_category VARCHAR(16) NOT NULL;
