CREATE TABLE reservations
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    item_id     BIGINT NOT NULL,
    reserved_by BIGINT NOT NULL,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_reservations_item
        FOREIGN KEY (item_id)
            REFERENCES wishlist_items (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_reservations_user
        FOREIGN KEY (reserved_by)
            REFERENCES users (id)
            ON DELETE CASCADE,

    CONSTRAINT uq_reservations_item
        UNIQUE (item_id)
);