package com.seat.mowerbot.domain.command;

import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Plateau;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationValidationTest {

    @Test
    void testIsValidLocation() {
        Plateau plateau = new Plateau(5, 5);
        Location location = new Location(3, 3);
        LocationValidation plateauBusiness = new LocationValidation(plateau, location);
        boolean isValid = plateauBusiness.isValid();
        assertTrue(isValid);
    }

    @Test
    void testIsNotValidLocationX() {
        Plateau plateau = new Plateau(5, 5);
        Location location = new Location(-1, 3);
        LocationValidation plateauBusiness = new LocationValidation(plateau, location);
        boolean isValid = plateauBusiness.isValid();
        assertFalse(isValid);
    }

    @Test
    void testIsNotValidLocationY() {
        Plateau plateau = new Plateau(5, 5);
        Location location = new Location(3, 6);
        LocationValidation plateauBusiness = new LocationValidation(plateau, location);
        boolean isValid = plateauBusiness.isValid();
        assertFalse(isValid);
    }

    @Test
    void testIsNotValidLocationAxisXTrue() {
        Plateau plateau = new Plateau(5, 5);
        Location location = new Location(-1, 3);
        LocationValidation plateauBusiness = new LocationValidation(plateau, location);
        boolean isNotValid = plateauBusiness.isNotValid();
        assertTrue(isNotValid);
    }

    @Test
    void testIsNotValidLocationAxisYTrue() {
        Plateau plateau = new Plateau(5, 5);
        Location location = new Location(3, -1);
        LocationValidation plateauBusiness = new LocationValidation(plateau, location);
        boolean isNotValid = plateauBusiness.isNotValid();
        assertTrue(isNotValid);
    }

}
