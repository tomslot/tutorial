package com.zooplus.forex.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<ForexUser, Long> {
    List<ForexUser> findByLogin(String login);
}
