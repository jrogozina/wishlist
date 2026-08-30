package io.github.jrogozina.wishlist.wishlist;

import java.time.OffsetDateTime;
import java.util.UUID;

public record WishlistResponse(
        Long id,
        String title,
        String description,
        UUID shareToken,
        OffsetDateTime createdAt
) {
}
