CREATE TABLE `linkedField` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `referenceUuid` varchar(255),
                               `fieldUuid` varchar(255),
                               `position` int NOT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `linkedField_id_uindex` (`id`),
                               KEY `referenceUuid` (`referenceUuid`),
                               KEY `fieldUuid` (`fieldUuid`),
                               CONSTRAINT `linkedfield_ibfk_1` FOREIGN KEY (`referenceUuid`) REFERENCES `uswReference` (`uuid`),
                               CONSTRAINT `linkedfield_ibfk_2` FOREIGN KEY (`fieldUuid`) REFERENCES `fields` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci