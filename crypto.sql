CREATE DATABASE crypto1;

CREATE TABLE `customerdata` (
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `btc_owned` double DEFAULT NULL,
  `btc_spent` double DEFAULT NULL,
  `eth_owned` double DEFAULT NULL,
  `eth_spent` double DEFAULT NULL,
  `sq` varchar(255) DEFAULT NULL,
  `sa` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
