package com.seat.mowerbot.infrastructure.rest;

import com.seat.mowerbot.application.service.EvaluateMowerCommandsService;
import com.seat.mowerbot.domain.model.MowerCommandType;
import com.seat.mowerbot.infrastructure.rest.dto.LocationDto;
import com.seat.mowerbot.infrastructure.rest.mapper.LocationMapper;
import com.seat.mowerbot.infrastructure.rest.mapper.PlateauMapper;
import com.seat.mowerbot.infrastructure.rest.mapper.MowerCommandMapper;
import com.seat.mowerbot.domain.model.Plateau;
import com.seat.mowerbot.infrastructure.rest.dto.MowersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/mower")
public class MowerController {

    private final EvaluateMowerCommandsService evaluateMowerCommandsService;

    private final PlateauMapper plateauMapper;

    private final MowerCommandMapper mowerCommandMapper;

    private final LocationMapper locationMapper;

    @Autowired
    public MowerController(EvaluateMowerCommandsService evaluateMowerCommandsService, PlateauMapper plateauMapper,
                           MowerCommandMapper mowerCommandMapper, LocationMapper locationMapper) {
        this.evaluateMowerCommandsService = evaluateMowerCommandsService;
        this.plateauMapper = plateauMapper;
        this.mowerCommandMapper = mowerCommandMapper;
        this.locationMapper = locationMapper;
    }

    @PostMapping("/commands")
    public List<LocationDto> evaluateCommands(@Valid @RequestBody MowersDto mowersDto) {
        Plateau plateau = plateauMapper.map(mowersDto.getPlateauRequest());
        return mowersDto.getMowers().stream()
                .map(mower -> {
                    List<MowerCommandType> commands = mowerCommandMapper.map(mower.getCommands());
                    return evaluateMowerCommandsService.evaluateCommands(plateau,
                            locationMapper.dtoToDomain(mower.getLocation()), commands);

                })
                .map(locationMapper::domainToDto)
                .collect(Collectors.toList());
    }

}
