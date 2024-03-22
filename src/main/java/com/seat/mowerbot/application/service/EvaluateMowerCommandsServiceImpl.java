package com.seat.mowerbot.application.service;

import com.seat.mowerbot.application.service.command.MowerCommandException;
import com.seat.mowerbot.domain.Location;
import com.seat.mowerbot.domain.MowerCommandType;
import com.seat.mowerbot.domain.Plateau;
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
        Location location = startLocation;
        for (MowerCommandType mowerCommandType : commands) {
            location = mowerCommandFactory.getCommand(location, mowerCommandType).execute();
            LocationValidation locationValidation = new LocationValidation(plateau, location);
            if (locationValidation.isNotValid()) {
                String message = String.format("Error invalid %s, for mower data with initial %s",
                        location, startLocation);
                throw new MowerCommandException(message);
            }
        }
        return location;
    }

}
