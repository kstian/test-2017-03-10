package com.github.ktarou;

import java.time.LocalDate;

/**
 *
 */
public class BillingFactory {
    public static Billing newBilling(UserType type, int registeredPeriod, double amount, BillingType billingType) {
        LocalDate registeredDate = LocalDate.now().minusYears(registeredPeriod);
        User user = new User(type, registeredDate);
        return new Billing(user, amount, billingType);
    }
}
