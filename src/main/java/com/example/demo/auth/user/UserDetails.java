package com.example.demo.auth.user;

import com.example.demo.core.users.infrastructure.entity.Permission;
import com.example.demo.core.users.infrastructure.entity.SystemRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Builder
@Setter
@Getter
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private Long id;

    private String username;

    private String email;

    private String password;

    private SystemRole role;

    private Set<Permission> permissions;

    public UserDetails(
            Long id,
            String username,
            String email,
            String password,
            SystemRole role,
            Set<Permission> permissions) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));

        for (Permission perms : permissions) {
            list.add(new SimpleGrantedAuthority(perms.getName()));
        }

        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getOriginalUserName() {
        return username;
    }
}
