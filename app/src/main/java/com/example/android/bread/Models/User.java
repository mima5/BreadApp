package com.example.android.bread.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

    @PrimaryKey
    private int id;

    private String name;
    private int flourAmount;
    private int balance;
    private int debt;


    public User() {
    }

    public User(int id, String name, int flourAmount, int balance, int debt) {
        this.id = id;
        this.name = name;
        this.flourAmount = flourAmount;
        this.balance = balance;
        this.debt = debt;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlourAmount() {
        return flourAmount;
    }

    public void setFlourAmount(int flourAmount) {
        this.flourAmount = flourAmount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    @Override
    public String toString() {
        return
                "  Name: " + name  +
                "   FlourAmount= " + flourAmount ;
    }
}
