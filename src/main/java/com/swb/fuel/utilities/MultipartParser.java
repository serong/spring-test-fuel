package com.swb.fuel.utilities;

import com.swb.fuel.models.FuelConsumption;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MultipartParser {

    private static Logger logger = LoggerFactory.getLogger(MultipartParser.class);

    public static List<FuelConsumption> fuelConsumptions(MultipartFile file) throws IOException, ParseException {
        List<FuelConsumption> fcList = new ArrayList<>();

        String fileContent = new String(file.getBytes());
        CSVParser parser = CSVParser.parse(fileContent, CSVFormat.EXCEL.withHeader());

        for (CSVRecord row: parser) {
            // Parameters from the row.
            String driverId = row.get("driver_id");
            String fuelType = row.get("fuel_type");
            BigDecimal volume = new BigDecimal(row.get("volume"));
            BigDecimal ppl = new BigDecimal(row.get("ppl"));
            String registered = row.get("registered");

            if (!InputValidation.isValidDriverid(driverId) || !InputValidation.isValidFuelType(fuelType) ||
                !InputValidation.isValidPPL(ppl) || !InputValidation.isValidVolume(volume) ||
                !InputValidation.isValidDateText(registered)) {

                logger.warn("Invalid parameter in row: " + row.toString());
                continue;
            }

            // TODO: Validation for row values.
            FuelConsumption fc = new FuelConsumption.Builder(registered)
                    .setDriverId(driverId)
                    .setFuelType(fuelType)
                    .setPpl(ppl)
                    .setVolume(volume)
                    .build();

            fcList.add(fc);
        }

        return fcList;
    }
}
