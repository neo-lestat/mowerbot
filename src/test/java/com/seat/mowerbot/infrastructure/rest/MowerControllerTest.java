package com.seat.mowerbot.infrastructure.rest;

import com.seat.mowerbot.application.service.EvaluateMowerCommandsService;
import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.domain.model.MowerCommandType;
import com.seat.mowerbot.domain.model.Plateau;
import com.seat.mowerbot.infrastructure.rest.dto.LocationDto;
import com.seat.mowerbot.infrastructure.rest.mapper.LocationMapper;
import com.seat.mowerbot.infrastructure.rest.dto.MowerDto;
import com.seat.mowerbot.infrastructure.rest.dto.MowersDto;
import com.seat.mowerbot.application.service.command.MowerCommandException;
import com.seat.mowerbot.infrastructure.rest.dto.PlateauDto;
import com.seat.mowerbot.infrastructure.rest.mapper.MowerMapper;
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
    private MowerMapper mowerMapper;

    @Mock
    private LocationMapper locationMapper;

    @Mock
    private EvaluateMowerCommandsService evaluateMowerCommandsService;

    @InjectMocks
    private MowerController mowerController;

    @Test
    void testEvaluateCommands() {
        MowersDto mowersDto = buildMowerDto();
        Mower mower = buildMower();
        Location finalLocation = new Location(0, 2, Cardinal.WEST);
        LocationDto finalLocationDto = new LocationDto(0, 2, Cardinal.WEST.getShortLetter());
        when(mowerMapper.mapToDomain(mowersDto.getPlateauDto(), mowersDto.getMowers().getFirst()))
                .thenReturn(mower);
        when(evaluateMowerCommandsService.evaluateCommands(mower))
                .thenReturn(finalLocation);
        when(locationMapper.domainToDto(finalLocation)).thenReturn(finalLocationDto);
        List<LocationDto> results = mowerController.evaluateCommands(mowersDto);
        assertEquals(1, results.size());
        assertEquals(finalLocationDto, results.getFirst());
    }

    private Mower buildMower() {
        Location initLocation = new Location(1, 2, Cardinal.NORTH);
        List<MowerCommandType> commands = Arrays.asList(MowerCommandType.LEFT, MowerCommandType.MOVE);
        Plateau plateau = new Plateau(5, 5);
        return new Mower(plateau, initLocation, commands);
    }

    private MowersDto buildMowerDto() {
        PlateauDto plateauDto = new PlateauDto(5, 5);
        LocationDto initLocationDto = new LocationDto(1, 2, Cardinal.NORTH.getShortLetter());
        String movements = "LM";
        MowerDto mowerDto = new MowerDto();
        mowerDto.setLocation(initLocationDto);
        mowerDto.setCommands(movements);
        MowersDto mowersDto = new MowersDto();
        mowersDto.setPlateauRequest(plateauDto);
        mowersDto.setMowers(Collections.singletonList(mowerDto));
        return mowersDto;
    }

    @Test
    void testEvaluateCommandsThrowsException() throws MowerCommandException {
        MowersDto mowersDto = buildMowerDto();
        Mower mower = buildMower();
        when(mowerMapper.mapToDomain(mowersDto.getPlateauDto(), mowersDto.getMowers().getFirst()))
                .thenReturn(mower);
        when(evaluateMowerCommandsService.evaluateCommands(mower))
                .thenThrow(new MowerCommandException("test"));
        assertThrows(MowerCommandException.class, () -> {
            mowerController.evaluateCommands(mowersDto);
        });
    }
}
