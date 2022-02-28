
DROP TABLE IF EXISTS book CASCADE;
DROP TABLE IF EXISTS author;

CREATE TABLE book
(
    id        BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    isbn      VARCHAR(255),
    publisher VARCHAR(255),
    title     VARCHAR(255),
    author_id BIGINT
) ENGINE = InnoDB;

create table author
(
    id         BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name  VARCHAR(255)
) ENGINE = InnoDB;

ALTER TABLE book
    ADD CONSTRAINT book_author_fk
        FOREIGN KEY (author_id) REFERENCES author (id);
