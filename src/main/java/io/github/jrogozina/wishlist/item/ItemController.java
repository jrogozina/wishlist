package io.github.jrogozina.wishlist.item;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/api/wishlists/{wishlistId}/items")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponse create(@PathVariable Long wishlistId,
                               @Valid @RequestBody CreateItemRequest request) {
        return itemService.create(wishlistId, request);
    }

    @GetMapping("/api/wishlists/{wishlistId}/items")
    public List<ItemResponse> getAllByWishlist(@PathVariable Long wishlistId) {
        return itemService.getAllByWishlist(wishlistId);
    }

    @GetMapping("/api/items/{id}")
    public ItemResponse getById(@PathVariable Long id) {
        return itemService.getById(id);
    }

    @PutMapping("/api/items/{id}")
    public ItemResponse update(@PathVariable Long id,
                               @Valid @RequestBody UpdateItemRequest request) {
        return itemService.update(id, request);
    }

    @DeleteMapping("/api/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }
}
