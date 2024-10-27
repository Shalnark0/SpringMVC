CREATE TABLE IF NOT EXISTS product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description TEXT,
    image_url VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2),
    product_code VARCHAR(50) UNIQUE NOT NULL
    );
