package com.example.demo.dto;

import com.example.demo.model.Address;

public class AccountDto {

    private Long accountId;

    private String userName;

    private String email;

    private Address address;

    private boolean canBuy;

    private boolean canSell;

    public AccountDto(Long accountId, String userName, String email,
                      Address address, boolean canBuy, boolean canSell) {
        this.accountId = accountId;
        this.userName = userName;
        this.email = email;
        this.address = address;
        this.canBuy = canBuy;
        this.canSell = canSell;
    }

    public AccountDto() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "accountId=" + accountId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", canBuy=" + canBuy +
                ", canSell=" + canSell +
                '}';
    }
}
