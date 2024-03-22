package com.seat.mowerbot.infrastructure.rest;

import com.seat.mowerbot.application.service.EvaluateMowerCommandsService;
import com.seat.mowerbot.domain.MowerCommandType;
import com.seat.mowerbot.infrastructure.rest.request.MowerData;
import com.seat.mowerbot.infrastructure.rest.request.PlateauMapper;
import com.seat.mowerbot.infrastructure.rest.request.MowerCommandMapper;
import com.seat.mowerbot.application.service.command.MowerCommandException;
import com.seat.mowerbot.domain.Location;
import com.seat.mowerbot.domain.Plateau;
import com.seat.mowerbot.infrastructure.rest.request.MowerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
        List<Location> locations = new ArrayList<>();
        try {
            for (MowerData mowerData : mowerRequest.getMowerDataList()) {
                List<MowerCommandType> commands = mowerCommandMapper.map(mowerData.getCommands());
                Location location = evaluateMowerCommandsService.evaluateCommands(plateau,
                        locationDtoMapper.dtoToDomain(mowerData.getLocation()), commands);
                locations.add(location);
            }
        } catch (MowerCommandException exc) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, exc.getMessage(), exc);
        }
        /*todo check how if MowerCommandException could be runtime
        locations = mowerRequest.getMowerDataList().stream()
                .map(mowerData -> {
                            try {
                                List<MowerCommandType> commands = stringToMowerCommandMapper.map(mowerData.getCommands());
                                return evaluateMowerCommandsService.evaluateCommands(plateau,
                                        locationMapper.dtoToDomain(mowerData.getLocation()), commands);
                            } catch (MowerCommandException e) {
                                throw new RuntimeException(e);
                            }
                        }
                ).collect(Collectors.toList());*/
        return locationDtoMapper.domainToDto(locations);
    }

}
