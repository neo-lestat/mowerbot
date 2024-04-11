package com.seat.mowerbot.infrastructure.rest;

import com.seat.mowerbot.application.service.MowerCommandsExecutor;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.domain.command.MowerCommandType;
import com.seat.mowerbot.infrastructure.rest.dto.LocationDto;
import com.seat.mowerbot.infrastructure.rest.dto.MowerDto;
import com.seat.mowerbot.infrastructure.rest.dto.PlateauDto;
import com.seat.mowerbot.infrastructure.rest.mapper.LocationMapper;
import com.seat.mowerbot.infrastructure.rest.mapper.MowerCommandTypeMapper;
import com.seat.mowerbot.infrastructure.rest.mapper.MowerMapper;
import com.seat.mowerbot.infrastructure.rest.dto.MowersDto;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/mower")
public class MowerController {

    private final MowerMapper mowerMapper;

    private final LocationMapper locationMapper;

    private final MowerCommandTypeMapper mowerCommandTypeMapper;

    private final MowerCommandsExecutor mowerCommandsExecutor;

    @Autowired
    public MowerController(MowerMapper mowerMapper, LocationMapper locationMapper,
                           MowerCommandTypeMapper mowerCommandTypeMapper,
                           MowerCommandsExecutor mowerCommandsExecutor) {
        this.mowerMapper = mowerMapper;
        this.locationMapper = locationMapper;
        this.mowerCommandTypeMapper = mowerCommandTypeMapper;
        this.mowerCommandsExecutor = mowerCommandsExecutor;
    }

    @PostMapping("/commands")
    public List<LocationDto> evaluateCommands(@Valid @RequestBody MowersDto mowersDto) {
        PlateauDto plateauDto = mowersDto.getPlateauDto();
        return mowersDto.getMowers().stream()
                .map(mowerDto -> evaluateCommands(plateauDto, mowerDto))
                .map(locationMapper::domainToDto)
                .collect(Collectors.toList());
    }

    private Location evaluateCommands(PlateauDto plateauDto, MowerDto mowerDto) {
        Mower mower = mowerMapper.mapToDomain(plateauDto, mowerDto);
        List<MowerCommandType> mowerCommands = mowerCommandTypeMapper.map(mowerDto.getCommands());
        mower = mowerCommandsExecutor.executeCommands(mower, mowerCommands);
        return mower.location();
    }

}
