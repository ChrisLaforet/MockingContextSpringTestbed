DROP TABLE IF EXISTS catalog;

CREATE TABLE catalog(
                        isbn VARCHAR(20) PRIMARY KEY,
                        title VARCHAR(100) NOT NULL,
                        author VARCHAR(100)
);

DROP TABLE IF EXISTS book;

CREATE TABLE book(
                     id INT AUTO_INCREMENT PRIMARY KEY,
                     isbn VARCHAR(20) NOT NULL,
                     price DOUBLE NOT NULL
);