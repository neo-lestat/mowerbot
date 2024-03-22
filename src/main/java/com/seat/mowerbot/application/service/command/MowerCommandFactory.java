package com.seat.mowerbot.application.service.command;

import com.seat.mowerbot.domain.Location;
import com.seat.mowerbot.domain.MowerCommandType;
import org.springframework.stereotype.Component;

@Component
public class MowerCommandFactory {

    public MowerCommand getCommand(Location location, MowerCommandType mowerCommandType) {
        MowerCommand command;
        switch (mowerCommandType) {
            case MOVE:
                command = new MoveCommand(location);
                break;
            case LEFT:
                command = new RotateLeftCommand(location);
                break;
            case RIGHT:
                command = new RotateRightCommand(location);
                break;
            default:
                throw new MowerCommandException("Wrong command type");
        }
        return command;
    }

}
