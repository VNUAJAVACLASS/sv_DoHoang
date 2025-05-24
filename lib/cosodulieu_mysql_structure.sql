
CREATE DATABASE IF NOT EXISTS cosodulieu;
USE cosodulieu;

-- Bảng môn học
CREATE TABLE tbl_subject (
    subject_code VARCHAR(10) PRIMARY KEY,
    subject_name VARCHAR(100),
    credit INT,
    attendanceexammark FLOAT,
    midexammark1 FLOAT,
    midexammark2 FLOAT,
    midexammark3 FLOAT,
    finalexammark FLOAT
);

-- Bảng người dùng (sinh viên hoặc giảng viên)
CREATE TABLE tbl_users (
    user_code VARCHAR(10) PRIMARY KEY,
    fullname VARCHAR(100),
    address VARCHAR(200),
    class VARCHAR(50),
    password VARCHAR(50),
    user_type VARCHAR(20)
);

-- Bảng đăng ký môn học + điểm của sinh viên
CREATE TABLE tbl_usersubject (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    user_code VARCHAR(10),
    subject_code VARCHAR(10),
    attendanceexammark FLOAT,
    midexammark1 FLOAT,
    midexammark2 FLOAT,
    midexammark3 FLOAT,
    finalexammark FLOAT,
    FOREIGN KEY (user_code) REFERENCES tbl_users(user_code),
    FOREIGN KEY (subject_code) REFERENCES tbl_subject(subject_code)
);
