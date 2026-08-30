package io.github.jrogozina.wishlist.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateItemRequest(
        @NotBlank(message = "Name must not be blank")
        @Size(max = 500, message = "Name must not exceed 500 characters")
        String name,

        @NotBlank(message = "Url must not be blank")
        String url,

        @PositiveOrZero(message = "Price must be positive or zero")
        BigDecimal price,

        String imageUrl
) {
}
