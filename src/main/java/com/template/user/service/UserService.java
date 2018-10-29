package com.template.user.service;

import com.template.user.vo.User;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

public interface UserService extends UserDetailsService{

    Collection<GrantedAuthority> getAuthorities(String username);
    public User readUser(String username);
    public void createUser(User user);
    public void deleteUser(String username);
    public PasswordEncoder passwordEncoder();
}
