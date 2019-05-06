package com.swb.fuel.controllers;

import com.swb.fuel.models.FuelConsumption;
import com.swb.fuel.repository.ConsumptionRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;

@RestController
@RequestMapping("/api/fuel")
@Api("Fuel consumption controller")
public class ConsumptionController {

    @Autowired
    private ConsumptionRepository consumptionRepository;

    @PostMapping("/add")
    public void addFuelConsumption(@RequestParam String fuelType, @RequestParam String driverId,
                                   @RequestParam BigDecimal ppl, @RequestParam BigDecimal volume,
                                   @RequestParam String registeredText) throws ParseException {

        FuelConsumption fc = new FuelConsumption();
        fc.setFuelType("99");
        fc.setDriverId("D00005");
        fc.setPpl(BigDecimal.valueOf(12L));
        fc.setVolume(BigDecimal.valueOf(10L));
        fc.setRegistered(new Date(System.currentTimeMillis()));

        FuelConsumption fc1 = new FuelConsumption.Builder(registeredText)
                .setDriverId(driverId)
                .setFuelType(fuelType)
                .setPpl(ppl)
                .setVolume(volume)
                .build();


        consumptionRepository.addFuelConsumption(fc.getFuelType(), fc.getPpl(), fc.getVolume(), fc.getRegistered(), fc.getDriverId());

        consumptionRepository.save(fc);
        consumptionRepository.save(fc1);
    }
}
