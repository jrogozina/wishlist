package io.github.jrogozina.wishlist.item;

import io.github.jrogozina.wishlist.common.NotFoundException;
import io.github.jrogozina.wishlist.wishlist.Wishlist;
import io.github.jrogozina.wishlist.wishlist.WishlistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {

    private final WishlistItemRepository wishlistItemRepository;
    private final WishlistRepository wishlistRepository;

    public ItemService(WishlistItemRepository wishlistItemRepository, WishlistRepository wishlistRepository) {
        this.wishlistItemRepository = wishlistItemRepository;
        this.wishlistRepository = wishlistRepository;
    }

    @Transactional
    public ItemResponse create(Long wishlistId, CreateItemRequest request) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new NotFoundException("Wishlist not found: " + wishlistId));

        WishlistItem item = new WishlistItem(wishlist, request.name(), request.url());

        item.setPrice(request.price());
        item.setImageUrl(request.imageUrl());

        WishlistItem saved = wishlistItemRepository.save(item);

        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> getAllByWishlist(Long wishlistId) {
        return wishlistItemRepository.findByWishlistId(wishlistId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ItemResponse getById(Long id) {
        WishlistItem item = wishlistItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found: " + id));
        return toResponse(item);
    }

    @Transactional
    public ItemResponse update(Long id, UpdateItemRequest request) {
        WishlistItem item = wishlistItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found: " + id));

        item.setName(request.name());
        item.setUrl(request.url());
        item.setPrice(request.price());
        item.setImageUrl(request.imageUrl());

        return toResponse(item);
    }

    @Transactional
    public void delete(Long id) {
        if (!wishlistItemRepository.existsById(id)) {
            throw new NotFoundException("Item not found: " + id);
        }
        wishlistItemRepository.deleteById(id);
    }

    private ItemResponse toResponse(WishlistItem item) {
        return new ItemResponse(
                item.getId(),
                item.getName(),
                item.getUrl(),
                item.getPrice(),
                item.getImageUrl(),
                item.getStatus(),
                item.getCreatedAt()
        );
    }
}
