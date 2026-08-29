package io.github.jrogozina.wishlist.wishlist;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WishlistResponse create(@Valid @RequestBody CreateWishlistRequest request,
                                   @RequestParam Long ownerId) {
        return wishlistService.create(request, ownerId);
    }

    @GetMapping("/{id}")
    public WishlistResponse getById(@PathVariable Long id) {
        return wishlistService.getById(id);
    }

    @GetMapping
    public List<WishlistResponse> getAllByOwner(@RequestParam Long ownerId) {
        return wishlistService.getAllByOwner(ownerId);
    }

    @PutMapping("/{id}")
    public WishlistResponse update(@PathVariable Long id,
                                   @Valid @RequestBody UpdateWishlistRequest request) {
        return wishlistService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        wishlistService.delete(id);
    }
}
