package com.seat.mowerbot.infrastructure.rest.mapper;

import com.seat.mowerbot.domain.command.MowerCommandType;
import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.domain.model.Plateau;
import com.seat.mowerbot.infrastructure.rest.dto.LocationDto;
import com.seat.mowerbot.infrastructure.rest.dto.MowerDto;
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
    @InjectMocks
    private MowerMapper mowerMapper;

    @Test
    void mapToDomain() {
        String commandsStr = "M";
        PlateauDto plateauDto = new PlateauDto(3, 3);
        LocationDto locationDto = new LocationDto(1, 1,   'N');
        MowerDto mowerDto = new MowerDto();
        mowerDto.setCommands(commandsStr);
        mowerDto.setLocation(locationDto);
        Plateau plateau = new Plateau(3, 3);
        Location location = new Location(1, 1, Cardinal.NORTH);
        when(plateauMapper.map(plateauDto)).thenReturn(plateau);
        when(locationMapper.dtoToDomain(locationDto)).thenReturn(location);
        Mower mower = mowerMapper.mapToDomain(plateauDto, mowerDto);
        assertEquals(plateau, mower.plateau());
        assertEquals(location, mower.location());
    }
}
