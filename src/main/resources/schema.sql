DROP TABLE IF EXISTS consumption;
CREATE TABLE consumption(
    id INT PRIMARY KEY AUTO_INCREMENT,
    fuel_type VARCHAR(5) NOT NULL,
    ppl DECIMAL NOT NULL,                       -- price per liter in EUR
    volume DECIMAL NOT NULL,
    registered DATE NOT NULL,
    driver_id VARCHAR(50) NOT NULL
);

