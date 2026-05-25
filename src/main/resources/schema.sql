CREATE DATABASE IF NOT EXISTS online_mall DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE online_mall;

DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS discount_strategy;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS region;

CREATE TABLE region (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    parent_id INT NOT NULL DEFAULT 0,
    INDEX idx_region_parent(parent_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role ENUM('buyer','seller','admin') NOT NULL,
    id_card VARCHAR(18) NOT NULL,
    province INT NOT NULL,
    city INT NOT NULL,
    district INT NOT NULL,
    reg_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_province FOREIGN KEY (province) REFERENCES region(id),
    CONSTRAINT fk_user_city FOREIGN KEY (city) REFERENCES region(id),
    CONSTRAINT fk_user_district FOREIGN KEY (district) REFERENCES region(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE discount_strategy (
    id INT PRIMARY KEY AUTO_INCREMENT,
    strategy_name VARCHAR(50) NOT NULL,
    strategy_class VARCHAR(120) NOT NULL,
    discount_value DECIMAL(10,2) NOT NULL DEFAULT 1.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    seller_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    original_price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    image_path VARCHAR(255) NOT NULL,
    discount_strategy_id INT NOT NULL,
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_product_seller(seller_id),
    CONSTRAINT fk_product_seller FOREIGN KEY (seller_id) REFERENCES user(id) ON DELETE CASCADE,
    CONSTRAINT fk_product_strategy FOREIGN KEY (discount_strategy_id) REFERENCES discount_strategy(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_no VARCHAR(40) NOT NULL UNIQUE,
    buyer_id INT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'CREATED',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_order_buyer FOREIGN KEY (buyer_id) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE order_item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    product_name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_order_item_order FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    CONSTRAINT fk_order_item_product FOREIGN KEY (product_id) REFERENCES product(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO region(id, name, parent_id) VALUES
(1, '北京市', 0), (2, '上海市', 0), (3, '广东省', 0),
(11, '北京市', 1), (12, '上海市', 2), (31, '广州市', 3), (32, '深圳市', 3),
(111, '海淀区', 11), (112, '朝阳区', 11), (121, '浦东新区', 12), (122, '徐汇区', 12),
(311, '天河区', 31), (312, '越秀区', 31), (321, '南山区', 32), (322, '福田区', 32);

INSERT INTO discount_strategy(strategy_name, strategy_class, discount_value) VALUES
('无折扣', 'com.shop.strategy.NoDiscountStrategy', 1.00),
('八折优惠', 'com.shop.strategy.PercentageDiscountStrategy', 0.80),
('九折优惠', 'com.shop.strategy.PercentageDiscountStrategy', 0.90);

INSERT INTO user(username, password, role, id_card, province, city, district) VALUES
('admin', 'admin123', 'admin', '110101199001011234', 1, 11, 111),
('seller', 'seller123', 'seller', '110101199002021234', 1, 11, 112),
('buyer', 'buyer123', 'buyer', '110101199003031234', 3, 32, 321);

INSERT INTO product(seller_id, name, description, original_price, stock, image_path, discount_strategy_id) VALUES
(2, '示例机械键盘', '青轴手感，适合办公与游戏。', 299.00, 20, 'uploads/default-keyboard.png', 2),
(2, '示例双肩包', '大容量通勤双肩包，轻便耐磨。', 159.00, 30, 'uploads/default-bag.png', 1);
