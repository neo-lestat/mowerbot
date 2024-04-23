package com.seat.mowerbot.infrastructure.rest;

import com.seat.mowerbot.domain.ApplyMowerCommandsUseCase;
import com.seat.mowerbot.domain.command.MowerCommandType;
import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.domain.model.Plateau;
import com.seat.mowerbot.infrastructure.rest.dto.LocationDto;
import com.seat.mowerbot.infrastructure.rest.dto.MowerResponseDto;
import com.seat.mowerbot.infrastructure.rest.dto.MowerDto;
import com.seat.mowerbot.infrastructure.rest.dto.MowersDto;
import com.seat.mowerbot.domain.command.MowerCommandException;
import com.seat.mowerbot.infrastructure.rest.dto.PlateauDto;
import com.seat.mowerbot.infrastructure.rest.mapper.MowerCommandTypeMapper;
import com.seat.mowerbot.infrastructure.rest.mapper.MowerMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MowerControllerTest {

    @Mock
    private MowerMapper mowerMapper;

    @Mock
    private MowerCommandTypeMapper mowerCommandTypeMapper;

    @Mock
    private ApplyMowerCommandsUseCase applyMowerCommandsUseCase;

    @InjectMocks
    private MowerController mowerController;

    @Test
    void testEvaluateCommands() {
        MowersDto mowersDto = aMowerDto();
        Mower mower = aMower();
        List<MowerCommandType> mowerCommandTypeList = Collections.singletonList(MowerCommandType.MOVE);
        when(mowerCommandTypeMapper.map(mowersDto.getMowers().getFirst().getCommands()))
                .thenReturn(mowerCommandTypeList);
        when(mowerMapper.dtoToDomain(mowersDto.getPlateauDto(), mowersDto.getMowers().getFirst()))
                .thenReturn(mower);
        Mower mowerAppliedCommands = new Mower.Builder().from(mower).withLocation(new Location(0, 1));
        when(applyMowerCommandsUseCase.applyCommands(mower, mowerCommandTypeList))
                .thenReturn(mowerAppliedCommands);
        MowerResponseDto mowerResponseDto = aMowerResponseDto();
        when(mowerMapper.domainToDto(mowerAppliedCommands)).thenReturn(mowerResponseDto);
        List<MowerResponseDto> results = mowerController.evaluateCommands(mowersDto);
        assertEquals(1, results.size());
        assertEquals(mowerResponseDto, results.getFirst());
    }

    private Mower aMower() {
        return new Mower(new Plateau(5, 5),
                new Location(0, 0),
                Cardinal.NORTH);
    }

    private MowerResponseDto aMowerResponseDto() {
        return new MowerResponseDto(new LocationDto(0, 1), "N");
    }

    private MowersDto aMowerDto() {
        PlateauDto plateauDto = new PlateauDto(5, 5);
        LocationDto initLocationDto = new LocationDto(1, 2);
        String movements = "LM";
        MowerDto mowerDto = new MowerDto();
        mowerDto.setLocation(initLocationDto);
        mowerDto.setCommands(movements);
        MowersDto mowersDto = new MowersDto();
        mowersDto.setPlateauRequest(plateauDto);
        mowersDto.setMowers(Collections.singletonList(mowerDto));
        return mowersDto;
    }

}
