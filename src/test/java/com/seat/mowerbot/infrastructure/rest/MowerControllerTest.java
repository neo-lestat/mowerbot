package com.seat.mowerbot.infrastructure.rest;

import com.seat.mowerbot.application.service.EvaluateMowerCommandsService;
import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.MowerCommandType;
import com.seat.mowerbot.domain.model.Plateau;
import com.seat.mowerbot.infrastructure.rest.dto.LocationDto;
import com.seat.mowerbot.infrastructure.rest.mapper.LocationMapper;
import com.seat.mowerbot.infrastructure.rest.dto.MowerDto;
import com.seat.mowerbot.infrastructure.rest.dto.MowersDto;
import com.seat.mowerbot.application.service.command.MowerCommandException;
import com.seat.mowerbot.infrastructure.rest.dto.PlateauDto;
import com.seat.mowerbot.infrastructure.rest.mapper.PlateauMapper;
import com.seat.mowerbot.infrastructure.rest.mapper.MowerCommandMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MowerControllerTest {

    @Mock
    private PlateauMapper plateauMapper;

    @Mock
    private MowerCommandMapper mowerCommandMapper;

    @Mock
    private LocationMapper locationMapper;

    @Mock
    private EvaluateMowerCommandsService evaluateMowerCommandsService;

    @InjectMocks
    private MowerController mowerController;

    @Test
    void testEvaluateCommands() {
        PlateauDto plateauDto = new PlateauDto(5, 5);
        LocationDto initLocationDto = new LocationDto(1, 2, Cardinal.NORTH.getShortLetter());
        Location initLocation = new Location(1, 2, Cardinal.NORTH);
        String movements = "LM";
        MowersDto mowersDto = buildMowerRequest(plateauDto, initLocationDto, movements);
        Location finalLocation = new Location(0, 2, Cardinal.WEST);
        LocationDto finalLocationDto = new LocationDto(0, 2, Cardinal.WEST.getShortLetter());
        List<MowerCommandType> commands = Arrays.asList(MowerCommandType.LEFT, MowerCommandType.MOVE);
        Plateau plateau = new Plateau(5, 5);
        when(plateauMapper.map(plateauDto)).thenReturn(plateau);
        when(mowerCommandMapper.map(movements)).thenReturn(commands);
        when(locationMapper.domainToDto(finalLocation)).thenReturn(finalLocationDto);
        when(locationMapper.dtoToDomain(initLocationDto)).thenReturn(initLocation);
        when(evaluateMowerCommandsService.evaluateCommands(plateau, initLocation, commands))
                .thenReturn(finalLocation);
        List<LocationDto> results = mowerController.evaluateCommands(mowersDto);
        assertEquals(1, results.size());
        assertEquals(finalLocationDto, results.get(0));
    }

    private MowersDto buildMowerRequest(PlateauDto plateauDto, LocationDto initLocation, String movements) {
        MowerDto mowerDto = new MowerDto();
        mowerDto.setLocation(initLocation);
        mowerDto.setCommands(movements);
        MowersDto mowersDto = new MowersDto();
        mowersDto.setPlateauRequest(plateauDto);
        mowersDto.setMowers(Collections.singletonList(mowerDto));
        return mowersDto;
    }

    @Test
    void testEvaluateCommandsThrowsException() throws MowerCommandException {
        PlateauDto plateauDto = new PlateauDto(5, 5);
        LocationDto initLocationDto = new LocationDto(1, 2, Cardinal.NORTH.getShortLetter());
        Location initLocation = new Location(1, 2, Cardinal.NORTH);
        String movements = "LM";
        MowersDto mowersDto = buildMowerRequest(plateauDto, initLocationDto, movements);
        List<MowerCommandType> commands = Arrays.asList(MowerCommandType.LEFT, MowerCommandType.MOVE);
        Plateau plateau = new Plateau(5, 5);
        when(plateauMapper.map(plateauDto)).thenReturn(plateau);
        when(mowerCommandMapper.map(movements)).thenReturn(commands);
        when(locationMapper.dtoToDomain(initLocationDto)).thenReturn(initLocation);
        when(evaluateMowerCommandsService.evaluateCommands(plateau, initLocation, commands))
                .thenThrow(new MowerCommandException("test"));
        assertThrows(MowerCommandException.class, () -> {
            mowerController.evaluateCommands(mowersDto);
        });
    }
}
