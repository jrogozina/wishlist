package io.github.jrogozina.wishlist.user;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue (strategy =  = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true, length = 50)
    private String username;

    @Column (nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private  String passwordHash;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDataTime createdAt;

}
