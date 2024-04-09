package com.seat.mowerbot.infrastructure.rest;

import com.seat.mowerbot.application.service.EvaluateMowerCommandsService;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.infrastructure.rest.dto.LocationDto;
import com.seat.mowerbot.infrastructure.rest.dto.MowerDto;
import com.seat.mowerbot.infrastructure.rest.dto.PlateauDto;
import com.seat.mowerbot.infrastructure.rest.mapper.LocationMapper;
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

    private final EvaluateMowerCommandsService evaluateMowerCommandsService;

    private final MowerMapper mowerMapper;

    private final LocationMapper locationMapper;

    @Autowired
    public MowerController(EvaluateMowerCommandsService evaluateMowerCommandsService,
                           MowerMapper mowerMapper, LocationMapper locationMapper) {
        this.evaluateMowerCommandsService = evaluateMowerCommandsService;
        this.mowerMapper = mowerMapper;
        this.locationMapper = locationMapper;
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
        return evaluateMowerCommandsService.evaluateCommands(mower);
    }

}
