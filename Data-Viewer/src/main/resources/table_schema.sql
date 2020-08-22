DROP TABLE IF EXISTS customer_info;
 
CREATE TABLE customer_info (
  c_id INT AUTO_INCREMENT  PRIMARY KEY,
  c_first_name VARCHAR(250) NOT NULL,
  c_last_name VARCHAR(250) NOT NULL,
  c_phone VARCHAR(250) NOT NULL,
  c_email VARCHAR(250) NOT NULL
  
);
