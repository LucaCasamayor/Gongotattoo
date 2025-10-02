-- schema.sql
CREATE TABLE adminuser (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           username VARCHAR(255) NOT NULL UNIQUE,
                           password VARCHAR(100) NOT NULL
);

CREATE TABLE media (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255),
                       url VARCHAR(255),
                       uploadedAt TIMESTAMP,
                       media_type VARCHAR(255)
);
