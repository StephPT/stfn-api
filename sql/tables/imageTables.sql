CREATE TABLE `gallery` (
                           `uuid` varchar(255) NOT NULL UNIQUE PRIMARY KEY,
                           `name` varchar(255) NOT NULL UNIQUE,
                           `dateCreated` date NOT NULL
);

CREATE TABLE `photo` (
                         `uuid` varchar(255) NOT NULL UNIQUE PRIMARY KEY,
                         `galleryId` varchar(255) NOT NULL,
                         `name` varchar(255) NOT NULL UNIQUE,
                         `uploaded` datetime NOT NULL,
                         `filepath` varchar(255) NOT NULL,
                         FOREIGN KEY (`galleryId`) REFERENCES `gallery` (`uuid`)
);

CREATE TABLE `attributes` (
                              `uuid` varchar(255) NOT NULL UNIQUE PRIMARY KEY ,
                              `photoID` varchar(255) NOT NULL,
                              `attribute` varchar(255) NOT NULL,
                              `value` varchar(255) NULL,
                              FOREIGN KEY (`photoID`) REFERENCES `photo` (`uuid`)
);
