package io.github.jrogozina.wishlist.item;

import io.github.jrogozina.wishlist.wishlist.Wishlist;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "wishlist_items")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WishlistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wishlist_id", nullable = false)
    private Wishlist wishlist;

   @Column (nullable = false, length = 500)
    private String name;

    @Column (nullable = false)
    private String url;

    @Column (precision = 10, scale = 2)
    private BigDecimal price;

    private String imageUrl;
    @Enumerated(EnumType.STRING)
    @Column (nullable = false)
    private ItemStatus status = ItemStatus.AVAILABLE;

    @Generated(event = EventType.INSERT)
    @Column(nullable = false, updatable = false, insertable = false)
    private OffsetDateTime createdAt;

    public WishlistItem(Wishlist wishlist, String name, String url) {
        this.wishlist = wishlist;
        this.name = name;
        this.url = url;
    }
}
