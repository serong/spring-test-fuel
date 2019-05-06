package com.swb.fuel.models.reports;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StatisticsReport implements Report {
    private String fuelType;
    private BigDecimal volume;
    private BigDecimal averagePrice;
    private BigDecimal totalPrice;
}
