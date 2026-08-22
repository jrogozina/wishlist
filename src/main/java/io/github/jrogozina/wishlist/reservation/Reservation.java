package io.github.jrogozina.wishlist.reservation;

import io.github.jrogozina.wishlist.item.WishlistItem;
import io.github.jrogozina.wishlist.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.time.OffsetDateTime;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private WishlistItem item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserved_by", nullable = false)
    private User reservedBy;

    @Generated(event = EventType.INSERT)
    @Column(nullable = false, updatable = false, insertable = false)
    private OffsetDateTime createdAt;

    public Reservation(WishlistItem item, User reservedBy) {
        this.item = item;
        this.reservedBy = reservedBy;
    }
}
