package cn.ann.beans;

import java.io.Serializable;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-3 9:26
 */
public class Account implements Serializable {
    private static final long serialVersionUID = -5436012481440032985L;

    private Integer id;
    private String accountName;
    private Double accountMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Double getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(Double accountMoney) {
        this.accountMoney = accountMoney;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", accountMoney='" + accountMoney + '\'' +
                '}';
    }
}
