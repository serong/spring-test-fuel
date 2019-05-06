package com.swb.fuel.models.reports;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Total Amount Report:
 * "Total spent amount of money grouped by month"
 */
public class TotalAmountReport implements Report {

    @Getter
    @Setter
    private BigDecimal totalAmount;

    @Getter
    @Setter
    private String month;

    @Override
    public String toString() {
        return String.format("Total: %s \t %f", month, totalAmount);
    }
}
