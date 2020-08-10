package com.seat.mowerbot.application.service.command;

import com.seat.mowerbot.application.service.command.*;
import com.seat.mowerbot.domain.Cardinal;
import com.seat.mowerbot.domain.Location;
import com.seat.mowerbot.domain.MowerCommandType;
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
    void testGetMoveCommand() throws MowerCommandException {
        MowerCommand command = mowerCommandFactory.getCommand(location, MowerCommandType.MOVE);
        assertNotNull(command);
        assertTrue(command instanceof MoveCommand);
    }

    @Test
    void testGetRotateLeftCommand() throws MowerCommandException {
        MowerCommand command = mowerCommandFactory.getCommand(location, MowerCommandType.LEFT);
        assertNotNull(command);
        assertTrue(command instanceof RotateLeftCommand);
    }

    @Test
    void testGetRotateRightCommand() throws MowerCommandException {
        MowerCommand command = mowerCommandFactory.getCommand(location, MowerCommandType.RIGHT);
        assertNotNull(command);
        assertTrue(command instanceof RotateRightCommand);
    }

    @Test
    void testGetThrowsException()  {
        location = new Location(3, 0, Cardinal.SOUTH);
        assertThrows(MowerCommandException.class, () -> {
            mowerCommandFactory.getCommand(location, MowerCommandType.UNKNOWN);
        });
    }

}