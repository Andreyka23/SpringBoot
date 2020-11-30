CREATE TABLE products (
      id          BIGSERIAL PRIMARY KEY,
      title        VARCHAR(255) NOT NULL,
      price       NUMERIC NOT NULL
);

INSERT INTO products (title, price) VALUES
('Apple', 5.40),
('Orange', 12.50),
('Beard', 5.40),
('Watermelon', 15.40),
('Beef', 200.00),
('Pork', 190.50),
('Chicken', 160.20),
("Coffee", 260.30),
('Tea', 180.50),
('Juice', 80.60);