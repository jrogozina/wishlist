package io.github.jrogozina.wishlist.wishlist;

import io.github.jrogozina.wishlist.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "wishlists")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false, length = 255)
    private String title;

    private String description;

    @Generated(event = EventType.INSERT)
    @Column(nullable = false, unique = true, insertable = false)
    private UUID shareToken;

    @Generated(event = EventType.INSERT)
    @Column(nullable = false, updatable = false, insertable = false)
    private OffsetDateTime createdAt;

    public Wishlist(User owner, String title, String description) {
        this.owner = owner;
        this.title = title;
        this.description = description;
    }
}
