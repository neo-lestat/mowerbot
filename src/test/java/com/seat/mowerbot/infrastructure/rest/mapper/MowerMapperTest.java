package com.seat.mowerbot.infrastructure.rest.mapper;

import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.domain.model.Plateau;
import com.seat.mowerbot.infrastructure.rest.dto.LocationDto;
import com.seat.mowerbot.infrastructure.rest.dto.MowerDto;
import com.seat.mowerbot.infrastructure.rest.dto.MowerResponseDto;
import com.seat.mowerbot.infrastructure.rest.dto.PlateauDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MowerMapperTest {

    @Mock
    private PlateauMapper plateauMapper;
    @Mock
    private LocationMapper locationMapper;
    @Mock
    private CardinalMapper cardinalMapper;
    @InjectMocks
    private MowerMapper mowerMapper;

    @Test
    void testMapDtoToDomain() {
        PlateauDto plateauDto = new PlateauDto(3, 3);
        MowerDto mowerDto = aMowerDto();
        Plateau plateau = new Plateau(3, 3);
        Location location = new Location(1, 1);
        when(plateauMapper.map(plateauDto)).thenReturn(plateau);
        when(locationMapper.dtoToDomain(mowerDto.getLocation())).thenReturn(location);
        when(cardinalMapper.map("N")).thenReturn(Cardinal.NORTH);
        Mower mower = mowerMapper.dtoToDomain(plateauDto, mowerDto);
        assertEquals(plateau, mower.plateau());
        assertEquals(location, mower.location());
    }

    @Test
    void testMapDomainToDro() {
        Mower mower = aMower();
        LocationDto locationDto = new LocationDto(1, 1);
        when(locationMapper.domainToDto(mower.location())).thenReturn(locationDto);
        MowerResponseDto mowerResponseDto = mowerMapper.domainToDto(mower);
        assertEquals(locationDto, mowerResponseDto.location());
        assertEquals("N", mowerResponseDto.direction());
    }

    private Mower aMower() {
        return new Mower(new Plateau(3, 3), new Location(1, 1), Cardinal.NORTH);
    }

    private MowerDto aMowerDto() {
        String commandsStr = "M";
        LocationDto locationDto = new LocationDto(1, 1);
        MowerDto mowerDto = new MowerDto();
        mowerDto.setCommands(commandsStr);
        mowerDto.setLocation(locationDto);
        mowerDto.setDirection("N");
        return mowerDto;
    }
}
