package io.github.jrogozina.wishlist.wishlist;

import jakarta.validation.constraints.*;

public record UpdateWishlistRequest(
        @NotBlank(message = "Title must not be blank")
        @Size(max = 255, message = "Title must not exceed 255 characters")
        String title,
        String description) {
}
