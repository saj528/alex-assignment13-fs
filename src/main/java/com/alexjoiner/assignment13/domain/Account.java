package com.alexjoiner.assignment13.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String accountName;

    //parent side of One to Many
    //mappedBy lets the parent class know what
    //  the name of the field is in the child class
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions = new ArrayList<>();

    //Child side of many to many
    //mappedBy points back to the field name of the parent
    //which in this case is `accounts`
    @ManyToMany(mappedBy = "accounts")
    private List<User> users = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


}
