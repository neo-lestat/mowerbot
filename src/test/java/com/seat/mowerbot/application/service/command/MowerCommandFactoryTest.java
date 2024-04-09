package com.seat.mowerbot.application.service.command;

import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.MowerCommandType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MowerCommandFactoryTest {

    private MowerCommandFactory mowerCommandFactory;
    private Location location;

    @BeforeEach
    void setUp() {
        mowerCommandFactory = new MowerCommandFactory();
        location = new Location(3, 3, Cardinal.EAST);
    }

    @Test
    void testGetMoveCommand() {
        MowerCommand command = mowerCommandFactory.getCommand(location, MowerCommandType.MOVE);
        assertNotNull(command);
        assertInstanceOf(MoveCommand.class, command);
    }

    @Test
    void testGetRotateLeftCommand() {
        MowerCommand command = mowerCommandFactory.getCommand(location, MowerCommandType.LEFT);
        assertNotNull(command);
        assertInstanceOf(RotateLeftCommand.class, command);
    }

    @Test
    void testGetRotateRightCommand() {
        MowerCommand command = mowerCommandFactory.getCommand(location, MowerCommandType.RIGHT);
        assertNotNull(command);
        assertInstanceOf(RotateRightCommand.class, command);
    }

}
