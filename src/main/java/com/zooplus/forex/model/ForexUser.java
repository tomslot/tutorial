package com.zooplus.forex.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Entity
public class ForexUser {
    private Long id;
    private String login;
    private String password;
    private String repeatedPassword;
    private List<CurrencyQuery> queries;
    private String email;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Size(min = 3, max = 16)
    @Column(unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Size(min = 3, max = 16)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Transient
    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
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
