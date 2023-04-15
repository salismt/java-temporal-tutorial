CREATE TABLE IF NOT EXISTS product_order (
    id BIGSERIAL PRIMARY KEY,
    reference_id VARCHAR(128),
    status VARCHAR(128),
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW(),
    price DECIMAL(15, 2),
    quantity INT,
    description TEXT
);
