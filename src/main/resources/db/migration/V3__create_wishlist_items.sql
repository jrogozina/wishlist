CREATE TABLE wishlist_items
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    wishlist_id BIGINT       NOT NULL,
    name        VARCHAR(500) NOT NULL,
    url         TEXT         NOT NULL,
    price       DECIMAL(10, 2),
    image_url   TEXT,
    status      VARCHAR(20)  NOT NULL DEFAULT 'AVAILABLE',
    created_at  TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_wishlist_items_wishlist
        FOREIGN KEY (wishlist_id)
            REFERENCES wishlists (id)
            ON DELETE CASCADE,

    CONSTRAINT chk_wishlist_items_status
        CHECK (status IN ('AVAILABLE', 'RESERVED', 'PURCHASED')),

    CONSTRAINT chk_wishlist_items_price
        CHECK (price >= 0)
);