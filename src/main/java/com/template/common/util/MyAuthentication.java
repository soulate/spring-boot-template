package com.template.common.util;

import com.template.user.vo.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Setter
@Getter
public class MyAuthentication extends UsernamePasswordAuthenticationToken {

    User user;

    public MyAuthentication(String id, String pw, List<GrantedAuthority> authList, User user) {
        super(id, pw, authList);
        this.user = user;
    }
}
