package com.seat.mowerbot.infrastructure.rest;

import com.seat.mowerbot.application.service.EvaluateMowerCommandsService;
import com.seat.mowerbot.domain.MowerCommandType;
import com.seat.mowerbot.infrastructure.rest.request.PlateauMapper;
import com.seat.mowerbot.infrastructure.rest.request.MowerCommandMapper;
import com.seat.mowerbot.domain.Plateau;
import com.seat.mowerbot.infrastructure.rest.request.MowerRequest;
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

    private final LocationDtoMapper locationDtoMapper;

    @Autowired
    public MowerController(EvaluateMowerCommandsService evaluateMowerCommandsService, PlateauMapper plateauMapper,
                           MowerCommandMapper mowerCommandMapper, LocationDtoMapper locationDtoMapper) {
        this.evaluateMowerCommandsService = evaluateMowerCommandsService;
        this.plateauMapper = plateauMapper;
        this.mowerCommandMapper = mowerCommandMapper;
        this.locationDtoMapper = locationDtoMapper;
    }

    @PostMapping("/commands")
    public List<LocationDto> evaluateCommands(@Valid @RequestBody MowerRequest mowerRequest) {
        Plateau plateau = plateauMapper.map(mowerRequest.getPlateauRequest());
        return mowerRequest.getMowerDataList().stream()
                .map(mowerData -> {
                    List<MowerCommandType> commands = mowerCommandMapper.map(mowerData.getCommands());
                    return evaluateMowerCommandsService.evaluateCommands(plateau,
                            locationDtoMapper.dtoToDomain(mowerData.getLocation()), commands);

                })
                .map(locationDtoMapper::domainToDto)
                .collect(Collectors.toList());
    }

}
