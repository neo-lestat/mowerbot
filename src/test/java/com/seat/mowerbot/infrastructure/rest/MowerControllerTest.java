package com.seat.mowerbot.infrastructure.rest;

import com.seat.mowerbot.application.service.EvaluateMowerCommandsService;
import com.seat.mowerbot.domain.Cardinal;
import com.seat.mowerbot.domain.Location;
import com.seat.mowerbot.domain.MowerCommandType;
import com.seat.mowerbot.domain.Plateau;
import com.seat.mowerbot.infrastructure.rest.request.MowerData;
import com.seat.mowerbot.infrastructure.rest.request.MowerRequest;
import com.seat.mowerbot.application.service.command.MowerCommandException;
import com.seat.mowerbot.infrastructure.rest.request.PlateauRequest;
import com.seat.mowerbot.infrastructure.rest.request.PlateauMapper;
import com.seat.mowerbot.infrastructure.rest.request.MowerCommandMapper;
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
    private LocationDtoMapper locationDtoMapper;

    @Mock
    private EvaluateMowerCommandsService evaluateMowerCommandsService;

    @InjectMocks
    private MowerController mowerController;

    @Test
    void evaluateCommands() {
        PlateauRequest plateauRequest = new PlateauRequest(5, 5);
        LocationDto initLocationDto = new LocationDto(1, 2, Cardinal.NORTH.getShortLetter());
        Location initLocation = new Location(1, 2, Cardinal.NORTH);
        String movements = "LM";
        MowerRequest mowerRequest = buildMowerRequest(plateauRequest, initLocationDto, movements);
        Location finalLocation = new Location(0, 2, Cardinal.WEST);
        LocationDto finalLocationDto = new LocationDto(0, 2, Cardinal.WEST.getShortLetter());
        List<MowerCommandType> commands = Arrays.asList(MowerCommandType.LEFT, MowerCommandType.MOVE);
        Plateau plateau = new Plateau(5, 5);
        when(plateauMapper.map(plateauRequest)).thenReturn(plateau);
        when(mowerCommandMapper.map(movements)).thenReturn(commands);
        when(locationDtoMapper.domainToDto(finalLocation)).thenReturn(finalLocationDto);
        when(locationDtoMapper.dtoToDomain(initLocationDto)).thenReturn(initLocation);
        when(evaluateMowerCommandsService.evaluateCommands(plateau, initLocation, commands))
                .thenReturn(finalLocation);
        List<LocationDto> results = mowerController.evaluateCommands(mowerRequest);
        assertEquals(1, results.size());
        assertEquals(finalLocationDto, results.get(0));
    }

    private MowerRequest buildMowerRequest(PlateauRequest plateauRequest, LocationDto initLocation, String movements) {
        MowerData mowerData = new MowerData();
        mowerData.setLocation(initLocation);
        mowerData.setCommands(movements);
        MowerRequest mowerRequest = new MowerRequest();
        mowerRequest.setPlateauRequest(plateauRequest);
        mowerRequest.setMowerDataList(Collections.singletonList(mowerData));
        return mowerRequest;
    }

    @Test
    void evaluateCommandsThrowsException() throws MowerCommandException {
        PlateauRequest plateauRequest = new PlateauRequest(5, 5);
        LocationDto initLocationDto = new LocationDto(1, 2, Cardinal.NORTH.getShortLetter());
        Location initLocation = new Location(1, 2, Cardinal.NORTH);
        String movements = "LM";
        MowerRequest mowerRequest = buildMowerRequest(plateauRequest, initLocationDto, movements);
        List<MowerCommandType> commands = Arrays.asList(MowerCommandType.LEFT, MowerCommandType.MOVE);
        Plateau plateau = new Plateau(5, 5);
        when(plateauMapper.map(plateauRequest)).thenReturn(plateau);
        when(mowerCommandMapper.map(movements)).thenReturn(commands);
        when(locationDtoMapper.dtoToDomain(initLocationDto)).thenReturn(initLocation);
        when(evaluateMowerCommandsService.evaluateCommands(plateau, initLocation, commands))
                .thenThrow(new MowerCommandException("test"));
        assertThrows(MowerCommandException.class, () -> {
            mowerController.evaluateCommands(mowerRequest);
        });
    }
}
