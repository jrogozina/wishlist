package io.github.jrogozina.wishlist.item;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ItemResponse(
        Long id,
        String name,
        String url,
        BigDecimal price,
        String imageUrl,
        ItemStatus status,
        OffsetDateTime createdAt
) {
}
