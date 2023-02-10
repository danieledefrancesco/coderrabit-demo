package com.tuimm.learningpath.domain.vehicles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FuelTypeTest {
    @Test
    void getCost_shouldReturnExpectedCost_afterSettingTheCost()
    {
        double expectedCost = 1.23;
        FuelType.PETROL.setCost(expectedCost);
        Assertions.assertEquals(expectedCost, FuelType.PETROL.getCost(), 0);
    }

    @Test
    void setCost_shouldThrowIllegalArgumentException_whenProvidedCostIsLowerThanZero()
    {
        Exception actualException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> FuelType.PETROL.setCost(-1));
        Assertions.assertEquals("cost must be greater than 0", actualException.getMessage());
    }
    @Test
    void setCost_shouldThrowIllegalArgumentException_whenProvidedCostIsEqualToZero()
    {
        Exception actualException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> FuelType.PETROL.setCost(0));
        Assertions.assertEquals("cost must be greater than 0", actualException.getMessage());
    }
}
