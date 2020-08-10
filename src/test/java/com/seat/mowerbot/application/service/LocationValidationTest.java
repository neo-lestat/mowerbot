package com.seat.mowerbot.application.service;

import com.seat.mowerbot.domain.Cardinal;
import com.seat.mowerbot.domain.Location;
import com.seat.mowerbot.domain.Plateau;
import com.seat.mowerbot.application.service.LocationValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationValidationTest {

    @Test
    void testIsValidLocation() {
        Plateau plateau = new Plateau(5,5);
        Location location = new Location(3,3, Cardinal.EAST);
        LocationValidation plateauBusiness = new LocationValidation(plateau, location);
        boolean isValid = plateauBusiness.isValid();
        assertTrue(isValid);
    }

    @Test
    void testIsNotValidLocationX() {
        Plateau plateau = new Plateau(5,5);
        Location location = new Location(-1,3, Cardinal.EAST);
        LocationValidation plateauBusiness = new LocationValidation(plateau, location);
        boolean isValid = plateauBusiness.isValid();
        assertFalse(isValid);
    }

    @Test
    void testIsNotValidLocationY() {
        Plateau plateau = new Plateau(5,5);
        Location location = new Location(3,6, Cardinal.EAST);
        LocationValidation plateauBusiness = new LocationValidation(plateau, location);
        boolean isValid = plateauBusiness.isValid();
        assertFalse(isValid);
    }

    @Test
    void testIsNotValidLocationAxisXTrue() {
        Plateau plateau = new Plateau(5,5);
        Location location = new Location(-1,3, Cardinal.EAST);
        LocationValidation plateauBusiness = new LocationValidation(plateau, location);
        boolean isNotValid = plateauBusiness.isNotValid();
        assertTrue(isNotValid);
    }

    @Test
    void testIsNotValidLocationAxisYTrue() {
        Plateau plateau = new Plateau(5,5);
        Location location = new Location(3,-1, Cardinal.EAST);
        LocationValidation plateauBusiness = new LocationValidation(plateau, location);
        boolean isNotValid = plateauBusiness.isNotValid();
        assertTrue(isNotValid);
    }

}