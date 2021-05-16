package com.panteapaliuc.movie.reviews.model;

import com.panteapaliuc.movie.reviews.utility.enUserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotEmpty(message = "Email is required")
    private String email;

    @ManyToMany
    @JoinTable(
            name = "watchlist",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "movieId")
    )
    private List<Movie> watchlist;

    @ManyToMany
    @JoinTable(
            name = "diary",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "movieId")
    )
    private List<Movie> diary;

    @Enumerated(EnumType.STRING)
    private enUserRole userRole;

    @OneToOne
    private UserInfo userInfo;


    public User(@NotBlank(message = "Username is required") String username,
                @NotBlank(message = "Password is required") String password,
                @Email @NotEmpty(message = "Email is required") String email,
                List<Movie> watchlist,
                List<Movie> diary,
                enUserRole userRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.watchlist = watchlist;
        this.diary = diary;
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
