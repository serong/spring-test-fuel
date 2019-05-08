package com.swb.fuel;

import com.swb.fuel.utilities.InputValidation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InputValidationTest {

    @Test
    public void driverIdValidationIsCorrect() {
        Assert.assertFalse(InputValidation.isValidDriverid(""));
        Assert.assertFalse(InputValidation.isValidDriverid(" "));
        Assert.assertFalse(InputValidation.isValidDriverid(null));
        Assert.assertTrue(InputValidation.isValidDriverid("D0001"));
    }

    @Test
    public void fuelTypeValidationIsCorrect() {
        Assert.assertFalse(InputValidation.isValidFuelType(""));
        Assert.assertFalse(InputValidation.isValidFuelType(" "));
        Assert.assertFalse(InputValidation.isValidFuelType(null));
        Assert.assertTrue(InputValidation.isValidFuelType("D0001"));
    }

    @Test
    public void bigDecimalValidationIsCorrect() {
        Assert.assertFalse(InputValidation.isValidPPL(new BigDecimal(-10)));
        Assert.assertFalse(InputValidation.isValidVolume(new BigDecimal(-10)));
    }

    @Test
    public void dateTextValidationIsCorrect() {
        Assert.assertFalse(InputValidation.isValidDateText(""));
        Assert.assertFalse(InputValidation.isValidDateText(null));
        Assert.assertTrue(InputValidation.isValidDateText("02.20.2019"));
        Assert.assertFalse(InputValidation.isValidDateText("22.10.2019"));

    }

}
