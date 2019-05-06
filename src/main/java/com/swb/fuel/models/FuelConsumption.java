package com.swb.fuel.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
@Table("consumption")
public class FuelConsumption {

    public static class Builder {

        private static String dateFormat = "MM.dd.yyyy";
        private Date registered;
        private String fuelType;
        private BigDecimal ppl;
        private BigDecimal volume;
        private String driverId;

        public Builder(String parseDate) throws ParseException {

            if (parseDate == null || parseDate.trim().equals("")) {
                this.registered = new Date(System.currentTimeMillis());
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                this.registered = new Date(sdf.parse(parseDate).getTime());
            }
        }

        public Builder setFuelType(String fuelType) {
            this.fuelType = fuelType;
            return this;
        }

        public Builder setPpl(BigDecimal ppl) {
            this.ppl = ppl;
            return this;
        }

        public Builder setVolume(BigDecimal volume) {
            this.volume = volume;
            return this;
        }

        public Builder setDriverId(String driverId) {
            this.driverId = driverId;
            return this;
        }

        public FuelConsumption build() {
            FuelConsumption fc = new FuelConsumption();
            fc.setFuelType(this.fuelType);
            fc.setDriverId(this.driverId);
            fc.setPpl(this.ppl);
            fc.setVolume(this.volume);
            fc.setRegistered(this.registered);

            return fc;
        }
    }

    @Id
    @JsonIgnore
    private Long id;

    private String fuelType;
    private BigDecimal ppl;
    private BigDecimal volume;
    private Date registered;
    private String driverId;
}
