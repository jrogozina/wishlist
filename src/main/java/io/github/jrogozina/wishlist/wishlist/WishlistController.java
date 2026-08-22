package io.github.jrogozina.wishlist.wishlist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WishlistResponse create(@RequestBody CreateWishlistRequest request,
                                   @RequestParam Long ownerId) {
        return wishlistService.create(request, ownerId);
    }
}
