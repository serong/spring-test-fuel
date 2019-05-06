package com.swb.fuel.models.reports;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Data
public class FuelConsumptionReport implements Report {
    private static String dateFormat = "MM.dd.yyyy";

    private String fuelType;
    private BigDecimal volume;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private String driverId;

    @JsonIgnore
    private Date registered;

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(getRegistered());
    }

}
