-- User Table
CREATE TABLE `User` (
  `UserID` VARCHAR(20) NOT NULL PRIMARY KEY,
  `UserName` VARCHAR(100) NOT NULL,
  `Email` VARCHAR(100) NOT NULL,
  `Account` VARCHAR(20) NOT NULL
);

-- Product Table
CREATE TABLE `Product` (
  `No` INT AUTO_INCREMENT PRIMARY KEY,
  `ProductName` VARCHAR(100) NOT NULL,
  `Price` DECIMAL(10,2) NOT NULL,
  `FeeRate` DECIMAL(5,4) NOT NULL
);

-- LikeList Table
CREATE TABLE `LikeList` (
  `SN` INT AUTO_INCREMENT PRIMARY KEY,
  `OrderName` INT NOT NULL,
  `Account` VARCHAR(20) NOT NULL,
  `ProductNo` INT NOT NULL,
  `UserID` VARCHAR(20) NOT NULL,
  `TotalFee` DECIMAL(10,2) NOT NULL,
  `TotalAmount` DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (`ProductNo`) REFERENCES `Product`(`No`),
  FOREIGN KEY (`UserID`) REFERENCES `User`(`UserID`)
);
