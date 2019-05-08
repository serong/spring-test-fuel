package com.swb.fuel.models.reports;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Statistics for months.
 */

@Data
public class StatisticsReport implements Report {
    private String fuelType;
    private BigDecimal volume;
    private BigDecimal averagePrice;
    private BigDecimal totalPrice;
}
