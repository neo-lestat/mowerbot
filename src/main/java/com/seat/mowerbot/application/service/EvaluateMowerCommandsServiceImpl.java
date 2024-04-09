package com.seat.mowerbot.application.service;

import com.seat.mowerbot.application.service.command.MowerCommandException;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.domain.model.MowerCommandType;
import com.seat.mowerbot.domain.model.Plateau;
import com.seat.mowerbot.application.service.command.MowerCommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluateMowerCommandsServiceImpl implements EvaluateMowerCommandsService {

    @Autowired
    private final MowerCommandFactory mowerCommandFactory;

    public EvaluateMowerCommandsServiceImpl(MowerCommandFactory mowerCommandFactory) {
        this.mowerCommandFactory = mowerCommandFactory;
    }

    @Override
    public Location evaluateCommands(Mower mower) {
        return mower.commands().stream()
                .reduce(mower.location(),
                        (locationPartial, commandType) -> evaluateCommand(mower.plateau(), locationPartial, commandType, mower.location()),
                        (a, b) -> b);
    }

    private Location evaluateCommand(Plateau plateau, Location locationOrigin,
                                     MowerCommandType mowerCommandType, Location startLocation) {
        Location location = mowerCommandFactory.getCommand(locationOrigin, mowerCommandType).execute();
        if (mowerCommandType.equals(MowerCommandType.MOVE)) {
            validateNewLocation(plateau, location, startLocation);
        }
        return location;
    }

    private void validateNewLocation(Plateau plateau, Location location, Location startLocation) {
        LocationValidation locationValidation = new LocationValidation(plateau, location);
        if (locationValidation.isNotValid()) {
            String message = String.format("Error invalid %s, for mower data with initial %s",
                    location, startLocation);
            throw new MowerCommandException(message);
        }
    }

}
