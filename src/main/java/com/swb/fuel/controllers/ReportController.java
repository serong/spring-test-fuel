package com.swb.fuel.controllers;

import com.swb.fuel.models.APIResponse;
import com.swb.fuel.models.reports.FuelConsumptionReport;
import com.swb.fuel.models.reports.StatisticsReport;
import com.swb.fuel.models.reports.TotalAmountReport;
import com.swb.fuel.repository.ReportRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/report")
@Api("Fuel consumption reports.")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    /**
     * Get total amount report for each month.
     *
     * @return
     */
    @PostMapping("/total")
    public ResponseEntity<APIResponse> getTotalAmountReport() {
        List<TotalAmountReport> amounts = reportRepository.getTotalAmountByMonth();
        return ResponseEntity.ok(new APIResponse("success", amounts, null));
    }

    /**
     * Get total amount report for each month for given driver.
     *
     * @param driverId String
     *
     * @return
     */
    @PostMapping("/total/driver")
    public ResponseEntity<APIResponse> getTotalAmountReportForDriver(@RequestParam String driverId) {
        List<TotalAmountReport> amounts = reportRepository.getTotalAmountByMonth(driverId);
        return ResponseEntity.ok(new APIResponse("success", amounts, null));
    }


    /**
     * Get fuel consumption report for given month
     *
     * @param month Integer
     *
     * @return
     */
    @PostMapping("/consumption")
    public ResponseEntity<APIResponse> getFuelConsumptionReport(@RequestParam Integer month) {
        List<FuelConsumptionReport> consumptionReports = reportRepository.getFuelConsumptionReportForMonth(month);
        return ResponseEntity.ok(new APIResponse("success", consumptionReports, null));
    }

    /**
     * Get fuel consumption report for given month and driver.
     *
     * @param month Integer
     * @param driverId String
     *
     * @return
     */
    @PostMapping("/consumption/driver")
    public ResponseEntity<APIResponse> getFuelConsumptionReportForDriver(@RequestParam Integer month, @RequestParam String driverId) {
        List<FuelConsumptionReport> consumptionReports = reportRepository.getFuelConsumptionReportForMonth(month, driverId);
        return ResponseEntity.ok(new APIResponse("success", consumptionReports, null));
    }

    /**
     * Get fuel consumptions statistics grouped by fuel type
     * @return
     */
    @PostMapping("/statistics")
    public ResponseEntity<APIResponse> getStatisticsReportByFuelType() {
        List<StatisticsReport> statisticsReports = reportRepository.getStatisticsReportByFuelType();
        return ResponseEntity.ok(new APIResponse("success", statisticsReports, null));
    }

    /**
     * Get fuel consumptions statistics grouped by fuel type
     *
     * @param driverId String
     *
     * @return
     */
    @PostMapping("/statistics/driver")
    public ResponseEntity<APIResponse> getStatisticsReportByFuelTypeForDriver(@RequestParam String driverId) {
        List<StatisticsReport> statisticsReports = reportRepository.getStatisticsReportByFuelType(driverId);
        return ResponseEntity.ok(new APIResponse("success", statisticsReports, null));
    }

    /**
     * Exception handlers for the controller.
     */

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<APIResponse> handleMissingRequestParameter(Exception ex) {
        return ResponseEntity.status(400).body(new APIResponse("error", null, ex.getMessage()));
    }

}
