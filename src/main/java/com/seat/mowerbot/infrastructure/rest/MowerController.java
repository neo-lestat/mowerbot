package com.seat.mowerbot.infrastructure.rest;

import com.seat.mowerbot.application.service.MowerService;
import com.seat.mowerbot.domain.MowerCommandType;
import com.seat.mowerbot.infrastructure.rest.request.MowerData;
import com.seat.mowerbot.infrastructure.rest.request.StringToMowerCommandMapper;
import com.seat.mowerbot.application.service.command.MowerCommandException;
import com.seat.mowerbot.domain.Location;
import com.seat.mowerbot.domain.Plateau;
import com.seat.mowerbot.infrastructure.rest.request.MowerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/mower")
public class MowerController {

    @Autowired
    private final StringToMowerCommandMapper stringToMowerCommandMapper;

    @Autowired
    private final MowerService mowerService;

    @Autowired
    private final LocationMapper locationMapper;

    public MowerController(MowerService mowerService, StringToMowerCommandMapper stringToMowerCommandMapper,
                           LocationMapper locationMapper) {
        this.mowerService = mowerService;
        this.stringToMowerCommandMapper = stringToMowerCommandMapper;
        this.locationMapper = locationMapper;
    }

    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }

    @PostMapping("/commands")
    public List<LocationDto> evaluateCommands(@Valid @RequestBody MowerRequest mowerRequest) {
        Plateau plateau = mowerRequest.getPlateau();
        List<Location> locations = new ArrayList<>();
        try {
            for (MowerData mowerData : mowerRequest.getMowerDataList()) {
                List<MowerCommandType> commands = stringToMowerCommandMapper.map(mowerData.getCommands());
                Location location = mowerService.evaluateCommands(plateau,
                        locationMapper.dtoToDomain(mowerData.getLocation()), commands);
                locations.add(location);
            }
        } catch (MowerCommandException exc) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, exc.getMessage(), exc);
        }
        return locationMapper.domainToDto(locations);
    }

}
