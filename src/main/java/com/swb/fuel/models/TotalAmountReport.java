package com.swb.fuel.models;

import java.math.BigDecimal;
import java.time.Month;

public class TotalAmountReport implements Report {
    private BigDecimal totalAmount;
    private String month;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return String.format("Total Amount: %f in Month:%s", totalAmount, month);
    }
}
