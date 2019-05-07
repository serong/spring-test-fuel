package com.swb.fuel.utilities;

import com.swb.fuel.models.FuelConsumption;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MultipartParser {

    public static List<FuelConsumption> fuelConsumptions(MultipartFile file) throws IOException, ParseException {
        List<FuelConsumption> fcList = new ArrayList<>();

        String fileContent = new String(file.getBytes());
        CSVParser parser = CSVParser.parse(fileContent, CSVFormat.EXCEL.withHeader());

        for (CSVRecord row: parser) {
            // TODO: Validation for row values.
            FuelConsumption fc = new FuelConsumption.Builder(row.get("registered"))
                    .setDriverId(row.get("driver_id"))
                    .setFuelType(row.get("fuel_type"))
                    .setPpl(new BigDecimal(row.get("ppl")))
                    .setVolume(new BigDecimal(row.get("volume")))
                    .build();

            fcList.add(fc);
        }

        return fcList;
    }
}
