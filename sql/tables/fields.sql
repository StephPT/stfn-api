CREATE TABLE fields
(
    name        VARCHAR(25) PRIMARY KEY NOT NULL,
    label       VARCHAR(25)             NOT NULL,
    type        VARCHAR(25)             NOT NULL,
    required    BOOLEAN                 NOT NULL
)