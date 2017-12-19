package com.sssarm.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cuiguiyang on 2017/5/14 22:32.
 * Desc:
 */
@Component
public class SpecifyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException("用户名为空");
        }

        // Login login = loginService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("用户不存在"));

        Set<GrantedAuthority> authorities = new HashSet<>();
        // roleService.getRoles(login.getId()).forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getName())));
        authorities.add(new SimpleGrantedAuthority("home"));
        authorities.add(new SimpleGrantedAuthority("hello"));

        String password = "$2a$08$YN2y9ehNoknkvyPn5iVNEe6b1ovsy8qmaNcJOyllzIsDvmIFn/Mpa";
        return new org.springframework.security.core.userdetails.User(
                username, password,
                true,//是否可用
                true,//是否过期
                true,//证书不过期为true
                true,//账户未锁定为true
                authorities);
    }
}
