package com.seat.mowerbot.domain.command;

import com.seat.mowerbot.domain.model.Mower;
import org.springframework.stereotype.Component;

@Component
public class MowerCommandFactory {

    public MowerCommand getCommand(Mower mower, MowerCommandType mowerCommandType) {
        MowerCommand command;
        switch (mowerCommandType) {
            case MOVE:
                command = new MoveCommand(mower);
                break;
            case LEFT:
                command = new RotateLeftCommand(mower);
                break;
            case RIGHT:
                command = new RotateRightCommand(mower);
                break;
            default:
                throw new MowerCommandException("Wrong command type");
        }
        return command;
    }

}
