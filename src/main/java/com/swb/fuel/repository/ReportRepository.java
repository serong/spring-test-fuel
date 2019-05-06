package com.swb.fuel.repository;

import com.swb.fuel.models.reports.FuelConsumptionReport;
import com.swb.fuel.models.reports.Report;
import com.swb.fuel.models.reports.StatisticsReport;
import com.swb.fuel.models.reports.TotalAmountReport;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends CrudRepository<Report, Long> {

    // total amount report by month.
    @Query("SELECT SUM(ppl * volume) as total_amount, MONTHNAME(registered) as month FROM consumption " +
           "GROUP BY MONTHNAME(registered)")
    List<TotalAmountReport> getTotalAmountByMonth();

    // total amounth report by month and driver.
    @Query("SELECT SUM(ppl * volume) as total_amount, MONTHNAME(registered) as month FROM consumption " +
           "WHERE driver_id = :driver_id " +
           "GROUP BY MONTHNAME(registered)")
    List<TotalAmountReport> getTotalAmountByMonth(@Param("driver_id") String forDriver);

    // fuel consumption report for month.
    @Query("SELECT fuel_type, volume, registered, ppl AS price, (ppl*volume) AS total_price, driver_id " +
           "FROM consumption " +
           "WHERE MONTH(registered) = :month")
    List<FuelConsumptionReport> getFuelConsumptionReportForMonth(@Param("month") Integer month);

    // fuel consumption report for month and driver.
    @Query("SELECT fuel_type, volume, registered, ppl AS price, (ppl*volume) AS total_price, driver_id " +
           "FROM consumption " +
           "WHERE MONTH(registered) = :month AND driver_id = :driver_id")
    List<FuelConsumptionReport> getFuelConsumptionReportForMonth(@Param("month") Integer month, @Param("driver_id") String driverId);

    // statics report grouped by fuel type.
    @Query("SELECT fuel_type, SUM(volume) AS volume, AVG(ppl) AS average_price, SUM(volume*ppl) AS total_price FROM consumption " +
           "GROUP BY fuel_type;")
    List<StatisticsReport> getStatisticsReportByFuelType();

    // statics report for driver grouped by fuel type.
    @Query("SELECT fuel_type, SUM(volume) AS volume, AVG(ppl) AS average_price, SUM(volume*ppl) AS total_price FROM consumption " +
           "WHERE driver_id = :driver_id " +
           "GROUP BY fuel_type;")
    List<StatisticsReport> getStatisticsReportByFuelType(@Param("driver_id") String driverId);
}
