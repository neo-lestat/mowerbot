package com.seat.mowerbot.application.service;

import com.seat.mowerbot.application.service.command.MowerCommandException;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.MowerCommandType;
import com.seat.mowerbot.domain.model.Plateau;
import com.seat.mowerbot.application.service.command.MowerCommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateMowerCommandsServiceImpl implements EvaluateMowerCommandsService {

    @Autowired
    private final MowerCommandFactory mowerCommandFactory;

    public EvaluateMowerCommandsServiceImpl(MowerCommandFactory mowerCommandFactory) {
        this.mowerCommandFactory = mowerCommandFactory;
    }

    public Location evaluateCommands(Plateau plateau, Location startLocation, List<MowerCommandType> commands) {
        Location location = new Location(startLocation.x(), startLocation.y(), startLocation.direction());
        return commands.stream()
                .reduce(location,
                        (locationPartial, commandType) -> evaluateCommand(plateau, locationPartial, commandType, startLocation),
                        (a, b) -> b);
    }

    private Location evaluateCommand(Plateau plateau, Location locationOrigin,
                                     MowerCommandType mowerCommandType, Location startLocation) {
        Location location = mowerCommandFactory.getCommand(locationOrigin, mowerCommandType).execute();
        LocationValidation locationValidation = new LocationValidation(plateau, location);
        if (locationValidation.isNotValid()) {
            String message = String.format("Error invalid %s, for mower data with initial %s",
                    location, startLocation);
            throw new MowerCommandException(message);
        }
        return location;
    }

}
