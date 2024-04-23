package com.seat.mowerbot.domain.command;

import org.springframework.stereotype.Component;

@Component
public class MowerCommandFactory {

    public MowerCommand getCommand(MowerCommandType mowerCommandType) {
        MowerCommand command;
        switch (mowerCommandType) {
            case MOVE:
                command = new MoveCommand();
                break;
            case LEFT:
                command = new RotateLeftCommand();
                break;
            case RIGHT:
                command = new RotateRightCommand();
                break;
            default:
                throw new MowerCommandException("Wrong command type");
        }
        return command;
    }

}
