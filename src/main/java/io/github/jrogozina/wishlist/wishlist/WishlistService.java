package io.github.jrogozina.wishlist.wishlist;

import io.github.jrogozina.wishlist.user.User;
import io.github.jrogozina.wishlist.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;

    public WishlistService(WishlistRepository wishlistRepository, UserRepository userRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public WishlistResponse create(CreateWishlistRequest request, Long ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + ownerId));

        Wishlist wishlist = new Wishlist(owner, request.title(), request.description());
        Wishlist saved = wishlistRepository.save(wishlist);
        return toResponse(saved);

    }

    @Transactional(readOnly = true)
    public WishlistResponse getById(Long id) {
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Wishlist not found: " + id));
        return toResponse(wishlist);
    }

    private WishlistResponse toResponse(Wishlist wishlist) {
        return new WishlistResponse(
                wishlist.getId(),
                wishlist.getTitle(),
                wishlist.getDescription(),
                wishlist.getShareToken(),
                wishlist.getCreatedAt());
    }
}

