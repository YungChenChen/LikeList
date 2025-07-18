DELIMITER //

CREATE PROCEDURE AddLikeProduct(
  IN p_userID VARCHAR(20),
  IN p_productNo INT,
  IN p_orderCount INT,
  IN p_account VARCHAR(20)
)
BEGIN
  DECLARE v_price DECIMAL(10,2);
  DECLARE v_feeRate DECIMAL(5,4);
  DECLARE v_totalAmount DECIMAL(10,2);
  DECLARE v_totalFee DECIMAL(10,2);

  SELECT Price, FeeRate INTO v_price, v_feeRate
  FROM Product WHERE No = p_productNo;

  SET v_totalAmount = v_price * p_orderCount;
  SET v_totalFee = v_totalAmount * v_feeRate;

  INSERT INTO LikeList (OrderName, Account, ProductNo, UserID, TotalFee, TotalAmount)
  VALUES (p_orderCount, p_account, p_productNo, p_userID, v_totalFee, v_totalAmount);
END //

DELIMITER ;
