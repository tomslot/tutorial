package com.zooplus.forex.security;

import com.zooplus.forex.model.ForexUser;
import com.zooplus.forex.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForexUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public ForexUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        List<ForexUser> result = userRepository.findByLogin(username);

        if (result.size() == 0){
            throw new UsernameNotFoundException("user not found");
        }

        ForexUser user = result.get(0);
        return new User(user.getLogin(), user.getPassword(), AuthorityUtils.createAuthorityList("USER"));
    }
}
