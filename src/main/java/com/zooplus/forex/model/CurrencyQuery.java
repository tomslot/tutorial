package com.zooplus.forex.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.Date;

@Entity
public class CurrencyQuery {
    private Long id;
    private CurrencyEnum srcCurrency;
    private CurrencyEnum dstCurrency;
    private Date timestamp;
    private Double amount;
    private Double convertedAmount;
    private ForexUser user;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    public CurrencyEnum getSrcCurrency() {
        return srcCurrency;
    }

    public void setSrcCurrency(CurrencyEnum srcCurrency) {
        this.srcCurrency = srcCurrency;
    }

    @Enumerated(EnumType.STRING)
    public CurrencyEnum getDstCurrency() {
        return dstCurrency;
    }

    public void setDstCurrency(CurrencyEnum dstCurrency) {
        this.dstCurrency = dstCurrency;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(Double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    @ManyToOne
    public ForexUser getUser() {
        return user;
    }

    public void setUser(ForexUser user) {
        this.user = user;
    }

    public String toShortString(){
        return String.format("%.2f %s = %.2f %s",
                getAmount(), getSrcCurrency(), getConvertedAmount(), getDstCurrency());
    }

    @Override
    public String toString() {
        return (new ReflectionToStringBuilder(this) {

            @Override
            protected Object getValue(Field field) throws IllegalArgumentException, IllegalAccessException {
                if ("user".equals(field.getName())){ // avoid infinite recursion
                    return ((ForexUser)field.get(CurrencyQuery.this)).getLogin();
                }

                return super.getValue(field);
            }
        }).toString();
    }
}
