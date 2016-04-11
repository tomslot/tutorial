package com.zooplus.forex.security;

import com.zooplus.forex.persistence.ForexUser;
import org.springframework.security.core.authority.AuthorityUtils;

public class SpringSecurityUser extends org.springframework.security.core.userdetails.User {
    private ForexUser user;

    public SpringSecurityUser(ForexUser user) {
        super(user.getLogin(), user.getPassword(), AuthorityUtils.createAuthorityList("USER"));
        this.user = user;
    }

    public ForexUser getUser() {
        return user;
    }
}