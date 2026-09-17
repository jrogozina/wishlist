package io.github.jrogozina.wishlist.user;

public record AuthResponse(
        String token,
        String type
) {
    public AuthResponse(String token) {
        this(token, "Bearer");
    }
}