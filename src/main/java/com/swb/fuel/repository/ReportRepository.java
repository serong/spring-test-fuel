package com.swb.fuel.repository;

import com.swb.fuel.models.FuelConsumptionReport;
import com.swb.fuel.models.Report;
import com.swb.fuel.models.TotalAmountReport;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReportRepository extends CrudRepository<Report, Long> {

    @Query("SELECT SUM(ppl * volume) as total_amount, MONTHNAME(registered) as month FROM CONSUMPTION " +
           "GROUP BY MONTHNAME(registered)")
    public List<TotalAmountReport> getTotalAmountByMonth();

    @Query("SELECT fuel_type, volume, registered, ppl, (ppl*volume) AS total_price, driver_id " +
           "FROM consumption " +
           "WHERE MONTH(registered) = 5")
    public List<FuelConsumptionReport> getFuelConsumptionReportForMonth();
}
