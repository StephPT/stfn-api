CREATE TABLE `directories` (
                          `uuid` varchar(255) NOT NULL,
                          `name` varchar(255) NOT NULL,
                          `path` varchar(255) NOT NULL,
                          `active` bit NOT NULL DEFAULT 1,
                          PRIMARY KEY (`uuid`),
                          UNIQUE KEY `fields_name_uindex` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci