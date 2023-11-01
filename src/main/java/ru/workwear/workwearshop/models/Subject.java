package ru.workwear.workwearshop.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.workwear.workwearshop.enums.Role;

import java.io.Serial;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "subjects")
@NoArgsConstructor(force = true)
public class Subject implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "placed_at")
    private Date placedAt;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "enabled")
    private boolean enabled;

    @PrePersist
    public void placedAt() {
        this.placedAt = new Date();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole().getRole()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}