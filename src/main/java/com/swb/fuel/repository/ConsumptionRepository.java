package com.swb.fuel.repository;

import com.swb.fuel.models.FuelConsumption;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.sql.Date;

public interface ConsumptionRepository extends CrudRepository<FuelConsumption, Long> {

    @Modifying
    @Query("INSERT INTO consumption(fuel_type, ppl, volume, registered, driver_id) VALUES " +
           "(:fuel_type, :ppl, :volume, :registered, :driver_id)")
    void addFuelConsumption(@Param("fuel_type") String fuelType, @Param("ppl") BigDecimal ppl,
                            @Param("volume") BigDecimal volume, @Param("registered")Date registered,
                            @Param("driver_id") String driverId);

    // Alternative way to add fuel consumption, as a model.
    @Override
    FuelConsumption save(FuelConsumption fc);
}
