package com.github.ktarou;

/**
 *
 */
public class Billing {
    private User user;
    private double amount;
    private BillingType type;

    public Billing(User user, double amount, BillingType type) {
        this.user = user;
        this.amount = amount;
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BillingType getType() {
        return type;
    }

    public void setType(BillingType type) {
        this.type = type;
    }
}
