package com.seat.mowerbot.infrastructure.rest;

import com.seat.mowerbot.application.service.command.MowerCommandException;
import com.seat.mowerbot.domain.Cardinal;
import com.seat.mowerbot.domain.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationDtoMapperTest {

    LocationDtoMapper locationDtoMapper;

    @BeforeEach
    void setUp() {
        locationDtoMapper = new LocationDtoMapper();
    }

    @Test
    void testDtoToDomain() throws MowerCommandException {
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

    @Test
    void testListDomainToListDto() {
        Location location = new Location(0, 2, Cardinal.WEST);
        LocationDto locationDto = new LocationDto(0, 2, Cardinal.WEST.getShortLetter());
        List<LocationDto> locationDtoList = locationDtoMapper.domainToDto(Collections.singletonList(location));
        assertEquals(locationDto, locationDtoList.get(0));
    }
}
