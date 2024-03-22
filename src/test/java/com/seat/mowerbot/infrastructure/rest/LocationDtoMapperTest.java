package com.seat.mowerbot.infrastructure.rest;

import com.seat.mowerbot.domain.Cardinal;
import com.seat.mowerbot.domain.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationDtoMapperTest {

    LocationDtoMapper locationDtoMapper;

    @BeforeEach
    void setUp() {
        locationDtoMapper = new LocationDtoMapper();
    }

    @Test
    void testDtoToDomain() {
        Location location = new Location(0, 2, Cardinal.WEST);
        LocationDto locationDto = new LocationDto(0, 2, Cardinal.WEST.getShortLetter());
        Location locationResult = locationDtoMapper.dtoToDomain(locationDto);
        assertEquals(location, locationResult);
    }

    @Test
    void testDomainToDto() {
        Location location = new Location(0, 2, Cardinal.WEST);
        LocationDto locationDto = new LocationDto(0, 2, Cardinal.WEST.getShortLetter());
        LocationDto locationDtoResult = locationDtoMapper.domainToDto(location);
        assertEquals(locationDto, locationDtoResult);
    }

}
