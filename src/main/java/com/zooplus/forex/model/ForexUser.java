package com.zooplus.forex.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

@Entity
public class ForexUser {
    private Long id;
    private String login;
    private String password;
    private List<CurrencyQuery> queries;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Size(min = 3, max = 16)
    @Column(unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @NotNull
    @Size(min = 3, max = 16)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    public List<CurrencyQuery> getQueries() {
        return queries;
    }

    public void setQueries(List<CurrencyQuery> queries) {
        this.queries = queries;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}
