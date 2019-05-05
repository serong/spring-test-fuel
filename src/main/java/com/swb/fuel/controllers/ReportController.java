package com.swb.fuel.controllers;

import com.swb.fuel.models.FuelConsumptionReport;
import com.swb.fuel.models.TotalAmountReport;
import com.swb.fuel.repository.ReportRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.util.List;

@RestController
@Api("Fuel consumption reports.")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @GetMapping("/report/test")
    public void test() {

        List<TotalAmountReport> ts = reportRepository.getTotalAmountByMonth();

        for (TotalAmountReport t: ts) {
            System.out.println(t);
        }

        List<FuelConsumptionReport> fs = reportRepository.getFuelConsumptionReportForMonth();

        for (FuelConsumptionReport f: fs) {
            System.out.println(">>> " + f.getTotalPrice());
        }

        System.out.println("Hello");

    }

}
