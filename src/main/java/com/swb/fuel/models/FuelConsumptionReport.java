package com.swb.fuel.models;

import java.math.BigDecimal;
import java.sql.Date;

public class FuelConsumptionReport implements Report {
    private String fuelType;
    private BigDecimal volume;
    private Date registered;
    private BigDecimal ppl;
    private BigDecimal totalPrice;
    private String driverId;

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public BigDecimal getPpl() {
        return ppl;
    }

    public void setPpl(BigDecimal ppl) {
        this.ppl = ppl;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
}
