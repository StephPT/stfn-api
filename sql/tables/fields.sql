CREATE TABLE `fields` (
                          `uuid` varchar(255) NOT NULL,
                          `label` varchar(25) NOT NULL,
                          `type` varchar(25) NOT NULL,
                          `required` tinyint(1) NOT NULL,
                          `placeholder` varchar(255) DEFAULT NULL,
                          `prefix` varchar(5) DEFAULT NULL,
                          `suffix` varchar(5) DEFAULT NULL,
                          `italic` tinyint(1) NOT NULL DEFAULT '0',
                          PRIMARY KEY (`uuid`),
                          UNIQUE KEY `fields_name_uindex` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci