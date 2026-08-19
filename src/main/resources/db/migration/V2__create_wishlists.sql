CREATE TABLE wishlists
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    owner_id    BIGINT       NOT NULL,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    share_token UUID                  DEFAULT gen_random_uuid() UNIQUE NOT NULL,
    created_at  TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_wishlists_owner
        FOREIGN KEY (owner_id)
            REFERENCES users (id)
            ON DELETE CASCADE
);