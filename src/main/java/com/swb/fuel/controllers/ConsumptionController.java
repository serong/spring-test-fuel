package com.swb.fuel.controllers;

import com.swb.fuel.models.APIResponse;
import com.swb.fuel.models.FuelConsumption;
import com.swb.fuel.repository.ConsumptionRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;

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
     * @return
     * @throws ParseException
     */
    @PostMapping("/add")
    public ResponseEntity<APIResponse> addFuelConsumption(@RequestParam String fuelType, @RequestParam String driverId,
                                   @RequestParam BigDecimal ppl, @RequestParam BigDecimal volume,
                                   @RequestParam String registered) throws ParseException {

        FuelConsumption fc = new FuelConsumption.Builder(registered)
                .setDriverId(driverId)
                .setFuelType(fuelType)
                .setPpl(ppl)
                .setVolume(volume)
                .build();

        consumptionRepository.save(fc);
        return ResponseEntity.ok(new APIResponse("success", null, null));
    }


    /**
     * Exception handlers for the controller.
     */

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<APIResponse> handleDateParseException(Exception ex) {
        return ResponseEntity.status(400).body(new APIResponse("error", null, ex.getMessage()));
    }
}
