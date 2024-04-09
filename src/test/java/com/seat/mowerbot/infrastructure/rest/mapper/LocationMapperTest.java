package com.seat.mowerbot.infrastructure.rest.mapper;

import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.infrastructure.rest.dto.LocationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationMapperTest {

    LocationMapper locationMapper;

    @BeforeEach
    void setUp() {
        locationMapper = new LocationMapper();
    }

    @Test
    void testDtoToDomain() {
        Location location = new Location(0, 2, Cardinal.WEST);
        LocationDto locationDto = new LocationDto(0, 2, Cardinal.WEST.getShortLetter());
        Location locationResult = locationMapper.dtoToDomain(locationDto);
        assertEquals(location, locationResult);
    }

    @Test
    void testDomainToDto() {
        Location location = new Location(0, 2, Cardinal.WEST);
        LocationDto locationDto = new LocationDto(0, 2, Cardinal.WEST.getShortLetter());
        LocationDto locationDtoResult = locationMapper.domainToDto(location);
        assertEquals(locationDto, locationDtoResult);
    }

}
