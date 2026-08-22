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

        return new WishlistResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getShareToken(),
                saved.getCreatedAt()
        );
    }
}

