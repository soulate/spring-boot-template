package com.template.user.service;

import com.template.common.util.CommonDao;
import com.template.user.vo.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("db1SqlSessionTemplate")
    private SqlSessionTemplate db1;

    @Autowired
    private CommonDao commonDao;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User readUser(String username) {
        User user = (User)commonDao.selectOne(db1,"UserMapper.readUser",username);
        user.setAuthorities(getAuthorities(username));
        return user;
    }

    @Override
    @Transactional
    public void createUser(User user) {
        String rawPassword = user.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
        user.setPassword(encodedPassword);

        commonDao.insert(db1,"UserMapper.createUser",user);
        commonDao.insert(db1,"UserMapper.createAuthority",user);
    }

    @Override
    @Transactional
    public void deleteUser(String username) {

        commonDao.delete(db1,"UserMapper.deleteUser",username);
        commonDao.delete(db1,"UserMapper.deleteAuthority",username);
    }

    @Override
    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loadUserByUsername :::: start.............");
        User user = (User)commonDao.selectOne(db1,"UserMapper.readUser",username);
        user.setAuthorities(getAuthorities(username));
        logger.info("loadUserByUsername :::: {} " + user.toString());
        return user;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities(String username) {
        Collection<GrantedAuthority> authorities = (List<GrantedAuthority>)commonDao.selectList(db1,"UserMapper.readAuthority",username);
        return authorities;
    }
}
