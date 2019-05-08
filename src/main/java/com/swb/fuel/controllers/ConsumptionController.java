package com.swb.fuel.controllers;

import com.swb.fuel.models.APIResponse;
import com.swb.fuel.models.FuelConsumption;
import com.swb.fuel.repository.ConsumptionRepository;
import com.swb.fuel.utilities.InputValidation;
import com.swb.fuel.utilities.MultipartParser;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/fuel")
@Api("Fuel consumption controller")
public class ConsumptionController {


    @Autowired
    private ConsumptionRepository consumptionRepository;

    /**
     * Add single fuel consumption record.
     *
     * @param fuelType String
     * @param driverId String
     * @param ppl BigDecimal
     * @param volume BigDecimal
     * @param registered String, date formatted as MM.dd.yyyy (12.30.2019)
     *
     * @return ResponseEntity
     * @throws ParseException (Parsing the date text)
     */
    @PostMapping("/add")
    public ResponseEntity<APIResponse> addFuelConsumption(@RequestParam String fuelType, @RequestParam String driverId,
                                   @RequestParam BigDecimal ppl, @RequestParam BigDecimal volume,
                                   @RequestParam String registered) throws ParseException {

        if (!InputValidation.isValidDriverid(driverId) || !InputValidation.isValidFuelType(fuelType) ||
            !InputValidation.isValidPPL(ppl) || !InputValidation.isValidVolume(volume) ||
            !InputValidation.isValidDateText(registered)) {

            return ResponseEntity.status(400).body(new APIResponse("error", null, "Invalid parameters"));
        }

        FuelConsumption fc = new FuelConsumption.Builder(registered)
                .setDriverId(driverId)
                .setFuelType(fuelType)
                .setPpl(ppl)
                .setVolume(volume)
                .build();

        FuelConsumption saved = consumptionRepository.save(fc);
        return ResponseEntity.ok(new APIResponse("success", saved, null));
    }


    /**
     * Add batch fuel consumption records from CSV file.
     *
     * Values in each row are validated in MultipartParser and if fails,
     * the row is skipped and written to the logs.
     *
     * @param file CSV
     *
     * @return ResponseEntity
     * @throws IOException (Reading the file)
     * @throws ParseException (Parsing the date text)
     */
    @PostMapping("/batch")
    public ResponseEntity<APIResponse> addBatchFuelConsumption(@RequestParam MultipartFile file) throws IOException, ParseException {

        List<FuelConsumption> fcList = MultipartParser.fuelConsumptions(file);
        Iterable<FuelConsumption> saved = consumptionRepository.saveAll(fcList);

        return ResponseEntity.ok(new APIResponse("success", saved, null));
    }


    /**
     * Exception handlers for the controller.
     */

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<APIResponse> handleDateParseException(Exception ex) {
        return ResponseEntity.status(400).body(new APIResponse("error", null, ex.getMessage()));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<APIResponse> handleIOException(Exception ex) {
        return ResponseEntity.status(400).body(new APIResponse("error", null, ex.getMessage()));
    }
}
