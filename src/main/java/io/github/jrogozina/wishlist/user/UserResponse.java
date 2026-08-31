package io.github.jrogozina.wishlist.user;

import java.time.OffsetDateTime;

public record UserResponse(
        Long id,
        String username,
        String email,
        OffsetDateTime createdAt
) {
}
