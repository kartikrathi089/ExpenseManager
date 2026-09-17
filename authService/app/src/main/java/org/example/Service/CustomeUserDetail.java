package org.example.Service;

import org.example.entities.UsersInfo;
import org.example.entities.UsersRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomeUserDetail extends UsersInfo implements UserDetails {

    private String username;




    private String password;

    Collection<? extends GrantedAuthority> authorities;

    public CustomeUserDetail(UsersInfo usersInfo) {
        this.username = usersInfo.getUsername();
        this.password = usersInfo.getPassword();
        List<GrantedAuthority> auths = new ArrayList<>();

        for(UsersRole role : usersInfo.getRoles()){
            auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
        }

        this.authorities = auths;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
