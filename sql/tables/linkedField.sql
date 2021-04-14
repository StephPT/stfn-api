CREATE TABLE linkedField
(
    uuid            VARCHAR(255)             NOT NULL,
    name            VARCHAR(25)              NOT NULL,
    FOREIGN KEY (uuid)  REFERENCES uswReference(uuid),
    FOREIGN KEY (name)  REFERENCES fields(name)
)