package com.github.ktarou;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;

/**
 *
 */
public class BillingCalculatorDiscountAware implements BillingCalculator {
    private static final double DISCOUNT_PERCENTAGE_30 = 0.3;
    private static final double DISCOUNT_PERCENTAGE_10 = 0.1;
    private static final double DISCOUNT_PERCENTAGE_5 = 0.05;

    @Override
    public double calculateAmmount(Billing billing) {
        double percentageDiscount = 0.0;
        if (billing.getType() == BillingType.NON_GROCERIES) {
            percentageDiscount = UserTypeDiscountCalculator.getCalculator(billing.getUser().getType()).calculateDiscount(billing);
        }
        double billDiscount = calculatePricingDiscount(billing.getAmount());
        return billing.getAmount() - (percentageDiscount + billDiscount);
    }

    private double calculatePricingDiscount(double amount) {
        // cast to int to ignore the fraction
        return ((int) (amount / 100)) * 5;
    }

    private enum UserTypeDiscountCalculator {
        DISCOUNT_30(UserType.EMPLOYEE, e -> e.getAmount() * DISCOUNT_PERCENTAGE_30),
        DISCOUNT_10(UserType.AFFILIATE, e -> e.getAmount() * DISCOUNT_PERCENTAGE_10),
        DISCOUNT_5(UserType.CUSTOMER, e -> {
            if (Period.between(e.getUser().getRegisteredDate(), LocalDate.now()).getYears() > 2) {
                return e.getAmount() * DISCOUNT_PERCENTAGE_5;
            }
            return 0.0;
        });

        private UserType type;
        private Function<Billing, Double> formula;

        UserTypeDiscountCalculator(UserType type, Function<Billing, Double> formula) {
            this.type = type;
            this.formula = formula;
        }

        double calculateDiscount(Billing billing) {
            return formula.apply(billing);
        }

        public static UserTypeDiscountCalculator getCalculator(UserType userType) {
            for (UserTypeDiscountCalculator op : values()) {
                if (op.type.equals(userType)) {
                    return op;
                }
            }
            throw new IllegalArgumentException("Unknown User type " + userType.toString());
        }
    }
}

