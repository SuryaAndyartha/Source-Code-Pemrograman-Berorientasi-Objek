CREATE DATABASE IF NOT EXISTS catalogue_sys;
USE catalogue_sys;

CREATE TABLE IF NOT EXISTS book_records (
    serial_id INT AUTO_INCREMENT PRIMARY KEY,
    book_title VARCHAR(200) NOT NULL,
    writer_name VARCHAR(150) NOT NULL,
    release_year YEAR,
    category_type VARCHAR(50)
);
