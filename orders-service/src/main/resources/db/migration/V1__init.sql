
CREATE TABLE orders (
    id                      bigserial PRIMARY KEY,
    price                   NUMERIC
);

CREATE TABLE order_items (
     id                      BIGSERIAL PRIMARY KEY,
     product_id              BIGSERIAL NOT NULL,
     order_id                BIGINT REFERENCES orders(id),
     price                   NUMERIC,
     price_per_product       NUMERIC,
     quantity                INT
);

INSERT INTO orders (id, price)
VALUES
(1, 100),
(2, 200),
(3, 300);

INSERT INTO order_items (product_id, order_id, price, price_per_product, quantity)
VALUES
(2, 1, 20, 10, 2),
(1, 1, 30, 5, 6),
(12, 1, 50, 5, 10),
(4, 2, 60, 5, 8),
(10, 2, 140, 70, 2),
(9, 3, 300, 50, 6);