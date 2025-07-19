-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS likelist;
USE likelist;

-- 刪除現有表（如果存在）
DROP TABLE IF EXISTS like_list;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS user;

-- user Table
CREATE TABLE user (
  user_id VARCHAR(20) NOT NULL PRIMARY KEY,
  user_name VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  account VARCHAR(20) NOT NULL
);

-- product Table
CREATE TABLE product (
  no VARCHAR(100) NOT NULL PRIMARY KEY,
  product_name VARCHAR(100) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  fee_rate DECIMAL(5,4) NOT NULL
);

-- like_list Table
CREATE TABLE like_list (
  sn INT AUTO_INCREMENT PRIMARY KEY,
  order_name INT NOT NULL,
  account VARCHAR(20) NOT NULL,
  product_no VARCHAR(20) NOT NULL,
  user_id VARCHAR(20) NOT NULL,
  total_fee DECIMAL(10,2) NOT NULL,
  total_amount DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (product_no) REFERENCES product(no),
  FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- 刪除現有存儲過程（如果存在）
DROP PROCEDURE IF EXISTS add_like_product;
DROP PROCEDURE IF EXISTS update_like_product;

-- 新增存儲過程
DELIMITER //
CREATE PROCEDURE add_like_product(
  IN p_user_id VARCHAR(20),
  IN p_product_no VARCHAR(20),
  IN p_order_count INT,
  IN p_account VARCHAR(20)
)
BEGIN
  DECLARE v_price DECIMAL(10,2);
  DECLARE v_fee_rate DECIMAL(5,4);
  DECLARE v_total_amount DECIMAL(10,2);
  DECLARE v_total_fee DECIMAL(10,2);
  DECLARE v_product_count INT;

  -- 檢查產品是否存在
  SELECT COUNT(*) INTO v_product_count FROM product WHERE no = p_product_no;
  IF v_product_count = 0 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Product not found';
  END IF;

  -- 取得產品資訊
  SELECT price, fee_rate INTO v_price, v_fee_rate FROM product WHERE no = p_product_no;
  IF v_price IS NULL THEN SET v_price = 0.00; END IF;
  IF v_fee_rate IS NULL THEN SET v_fee_rate = 0.00; END IF;

  SET v_total_amount = v_price * p_order_count;
  SET v_total_fee = v_total_amount * v_fee_rate;

  INSERT INTO like_list (order_name, account, product_no, user_id, total_fee, total_amount)
  VALUES (p_order_count, p_account, p_product_no, p_user_id, v_total_fee, v_total_amount);
END //

CREATE PROCEDURE update_like_product(
  IN p_sn INT,
  IN p_user_id VARCHAR(20),
  IN p_product_no VARCHAR(20),
  IN p_order_count INT,
  IN p_account VARCHAR(20)
)
BEGIN
  DECLARE v_price DECIMAL(10,2);
  DECLARE v_fee_rate DECIMAL(5,4);
  DECLARE v_total_amount DECIMAL(10,2);
  DECLARE v_total_fee DECIMAL(10,2);
  DECLARE v_product_count INT;
  DECLARE v_record_count INT;

  -- 檢查紀錄是否存在
  SELECT COUNT(*) INTO v_record_count FROM like_list WHERE sn = p_sn;
  IF v_record_count = 0 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Record not found';
  END IF;

  -- 檢查產品是否存在
  SELECT COUNT(*) INTO v_product_count FROM product WHERE no = p_product_no;
  IF v_product_count = 0 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Product not found';
  END IF;

  -- 取得產品資訊
  SELECT price, fee_rate INTO v_price, v_fee_rate FROM product WHERE no = p_product_no;
  IF v_price IS NULL THEN SET v_price = 0.00; END IF;
  IF v_fee_rate IS NULL THEN SET v_fee_rate = 0.00; END IF;

  SET v_total_amount = v_price * p_order_count;
  SET v_total_fee = v_total_amount * v_fee_rate;

  UPDATE like_list 
  SET order_name = p_order_count, 
      account = p_account, 
      product_no = p_product_no, 
      user_id = p_user_id, 
      total_fee = v_total_fee, 
      total_amount = v_total_amount
  WHERE sn = p_sn;
END //
DELIMITER ;

-- 插入測試資料
INSERT INTO user (user_id, user_name, email, account)
VALUES ('A123456789', '王小明', 'test@email.com', '1111999666');

INSERT INTO product (no, product_name, price, fee_rate)
VALUES ('A001', '基金A', 1000.00, 0.01), ('B001', '保單B', 5000.00, 0.005); 

INSERT INTO like_list (order_name, account, product_no, user_id, total_fee, total_amount)
VALUES (1, '1111999666', 'A001', 'A123456789', 10.00, 1000.00);