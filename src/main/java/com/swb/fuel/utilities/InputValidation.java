package com.swb.fuel.utilities;

import com.swb.fuel.models.FuelConsumption;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class InputValidation {

    public static boolean isValidDriverid(String driverId) {
        return driverId !=null && !driverId.trim().equals("");
    }

    public static boolean isValidFuelType(String fuelType) {
        return fuelType !=null && !fuelType.trim().equals("");
    }

    public static boolean isValidPPL(BigDecimal ppl) {
        return ppl != null && ppl.compareTo(BigDecimal.ZERO) > 0;
    }

    public static boolean isValidVolume(BigDecimal volume) {
        return volume != null && volume.compareTo(BigDecimal.ZERO) > 0;
    }

    public static boolean isValidDateText(String dateAsText) {

        if (dateAsText == null || dateAsText.trim().equals("") || !dateAsText.matches("[01]\\d.[0-3]\\d.\\d{4}")) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
        sdf.setLenient(false);

        try {
            sdf.parse(dateAsText);
            return true;
        } catch (ParseException e) {
            // e.printStackTrace();
            return false;
        }

    }
}
