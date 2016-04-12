package com.zooplus.forex.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<ForexUser, Long> {
    List<ForexUser> findByLogin(String login);
    @Query("select q from CurrencyQuery q join q.user u where u.id = ?#{[0]} order by q.timestamp desc")
    Page<CurrencyQuery> findLastQueriesForUser(Long userId, Pageable page);
}
