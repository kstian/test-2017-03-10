package com.github.ktarou;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class BillingCalculatorDiscountAwareTest {
    private BillingCalculator calculator = new BillingCalculatorDiscountAware();

    @Test
    public void calculateAmmountForEmployeeEligiblePriceDiscount_30PercentAndPriceDiscountApplied() {
        Billing billing = BillingFactory.newBilling(UserType.EMPLOYEE, 3, 990, BillingType.NON_GROCERIES);
        double netAmount = calculator.calculateAmmount(billing);
        System.out.println(netAmount);
        Assert.assertTrue(netAmount == 648); // 990 - 297 - 45
    }

    @Test
    public void calculateAmmountForEmployee_30PercentDiscountApplied() {
        Billing billing = BillingFactory.newBilling(UserType.EMPLOYEE, 3, 50, BillingType.NON_GROCERIES);
        double netAmount = calculator.calculateAmmount(billing);
        Assert.assertTrue(netAmount == 35); // 50 - 15
    }

    @Test
    public void calculateAmmountForAffiliateEligiblePriceDiscount_10PercentAndPriceDiscountApplied() {
        Billing billing = BillingFactory.newBilling(UserType.AFFILIATE, 1, 990, BillingType.NON_GROCERIES);
        double netAmount = calculator.calculateAmmount(billing);
        Assert.assertTrue(netAmount == 846); // 990 - 99 - 45
    }

    @Test
    public void calculateAmmountForAffiliate_10PercentDiscountApplied() {
        Billing billing = BillingFactory.newBilling(UserType.AFFILIATE, 1, 50, BillingType.NON_GROCERIES);
        double netAmount = calculator.calculateAmmount(billing);
        Assert.assertTrue(netAmount == 45); // 50 - 5
    }

    @Test
    public void calculateAmmountForCustomerEligiblePriceDiscount_5PercentAndPriceDiscountApplied() {
        Billing billing = BillingFactory.newBilling(UserType.CUSTOMER, 3, 990, BillingType.NON_GROCERIES);
        double netAmount = calculator.calculateAmmount(billing);
        Assert.assertTrue(netAmount == 895.5); // 990 - 49.5 - 45
    }

    @Test
    public void calculateAmmountForCustomer_5PercentDiscountApplied() {
        Billing billing = BillingFactory.newBilling(UserType.CUSTOMER, 1, 50, BillingType.NON_GROCERIES);
        double netAmount = calculator.calculateAmmount(billing);
        Assert.assertTrue(netAmount == 50);
    }

    @Test
    public void calculateAmmountForEmployeeEligiblePriceDiscountGroceries_OnlyPriceDiscountApplied() {
        Billing billing = BillingFactory.newBilling(UserType.EMPLOYEE, 3, 990, BillingType.GROCERIES);
        double netAmount = calculator.calculateAmmount(billing);
        Assert.assertTrue(netAmount == 945); // 990 - 45
    }

    @Test
    public void calculateAmmountForEmployeeGroceries_NoDiscountApplied() {
        Billing billing = BillingFactory.newBilling(UserType.EMPLOYEE, 3, 50, BillingType.GROCERIES);
        double netAmount = calculator.calculateAmmount(billing);
        Assert.assertTrue(netAmount == 50);
    }

    @Test
    public void calculateAmmountForAffiliateEligiblePriceDiscountGroceries_OnlyPriceDiscountApplied() {
        Billing billing = BillingFactory.newBilling(UserType.AFFILIATE, 1, 990, BillingType.GROCERIES);
        double netAmount = calculator.calculateAmmount(billing);
        Assert.assertTrue(netAmount == 945); // 990 - 45
    }

    @Test
    public void calculateAmmountForAffiliateGroceries_NoDiscountApplied() {
        Billing billing = BillingFactory.newBilling(UserType.AFFILIATE, 1, 50, BillingType.GROCERIES);
        double netAmount = calculator.calculateAmmount(billing);
        Assert.assertTrue(netAmount == 50);
    }

    @Test
    public void calculateAmmountForCustomerEligiblePriceDiscountGroceries_OnlyPriceDiscountApplied() {
        Billing billing = BillingFactory.newBilling(UserType.CUSTOMER, 3, 990, BillingType.GROCERIES);
        double netAmount = calculator.calculateAmmount(billing);
        Assert.assertTrue(netAmount == 945); // 990 - 45
    }

    @Test
    public void calculateAmmountForCustomerGroceries_NoDiscountApplied() {
        Billing billing = BillingFactory.newBilling(UserType.CUSTOMER, 1, 50, BillingType.GROCERIES);
        double netAmount = calculator.calculateAmmount(billing);
        Assert.assertTrue(netAmount == 50);
    }
}
