CREATE TABLE `linkedField` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `uuid` varchar(255) DEFAULT NULL,
                               `name` varchar(25) NOT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `linkedField_id_uindex` (`id`),
                               KEY `uuid` (`uuid`),
                               KEY `name` (`name`),
                               CONSTRAINT `linkedfield_ibfk_1` FOREIGN KEY (`uuid`) REFERENCES `uswReference` (`uuid`),
                               CONSTRAINT `linkedfield_ibfk_2` FOREIGN KEY (`name`) REFERENCES `fields` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci