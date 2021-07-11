package com.seat.mowerbot.infrastructure.rest;

import com.seat.mowerbot.domain.Cardinal;
import com.seat.mowerbot.domain.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationMapperTest {

    LocationMapper locationMapper;

    @BeforeEach
    void setUp() {
        locationMapper = new LocationMapper();
    }

    @Test
    void testDtoToDomain() {
        Location location = new Location(  0, 2, Cardinal.WEST);
        LocationDto locationDto = new LocationDto(  0, 2, Cardinal.WEST.getShortLetter());
        Location locationResult = locationMapper.dtoToDomain(locationDto);
        assertEquals(location, locationResult);
    }

    @Test
    void testDomainToDto() {
        Location location = new Location(  0, 2, Cardinal.WEST);
        LocationDto locationDto = new LocationDto(  0, 2, Cardinal.WEST.getShortLetter());
        LocationDto locationDtoResult = locationMapper.domainToDto(location);
        assertEquals(locationDto, locationDtoResult);
    }

    @Test
    void testListDomainToListDto() {
        Location location = new Location(  0, 2, Cardinal.WEST);
        LocationDto locationDto = new LocationDto(  0, 2, Cardinal.WEST.getShortLetter());
        List<LocationDto> locationDtoList = locationMapper.domainToDto(Collections.singletonList(location));
        assertEquals(locationDto, locationDtoList.get(0));
    }
}