package com.ishanitech.iaccountingrest.model;
import java.util.*;
import java.util.stream.Collectors;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * {@code User} represents the user entity.
 * User entity also represents the authenticated user on the system.
 * @author Umesh Bhujel
 * @since 1.0
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User implements UserDetails {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
//    private Date createdDate;
//    private Date editedDate;
    private Set<Role> roles = new HashSet<>();;
//  private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> userRoles = this.roles.stream().map(authority-> new SimpleGrantedAuthority(String.format("ROLE_%s", authority.getRole()))).collect(Collectors.toList());
        return  userRoles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
