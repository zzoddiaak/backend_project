package senla.config;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import senla.entities.User;

import java.util.Collection;
import java.util.List;

public class UserDetailsConfig implements UserDetails {
    @Getter
    private Long id;
    private String email;
    private String password;
    private SimpleGrantedAuthority authority;

    public UserDetailsConfig(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getUserPassword();
        this.authority = new SimpleGrantedAuthority(user.getRole().getRoleName().getAuthority());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(authority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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
