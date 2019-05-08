package com.swb.fuel;

import com.swb.fuel.models.FuelConsumption;
import com.swb.fuel.models.reports.StatisticsReport;
import com.swb.fuel.repository.ConsumptionRepository;
import com.swb.fuel.repository.ReportRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumptionRepositoryTest {

    @Autowired
    private ConsumptionRepository consumptionRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Test
    public void recordIsAddedSuccessfully() throws Exception {
        FuelConsumption fc = new FuelConsumption.Builder("01.01.2020")
                .setVolume(new BigDecimal(10))
                .setPpl(new BigDecimal(20))
                .setFuelType("D")
                .setDriverId("D0009")
                .build();

        FuelConsumption saved = consumptionRepository.save(fc);
        Assert.assertNotNull(saved.getId());
        Assert.assertEquals(fc.getDriverId(), saved.getDriverId());
    }
}
