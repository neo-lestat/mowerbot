package com.seat.mowerbot.domain.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MowerCommandFactoryTest {

    private MowerCommandFactory mowerCommandFactory;

    @BeforeEach
    void setUp() {
        mowerCommandFactory = new MowerCommandFactory();
    }

    @Test
    void testGetMoveCommand() {
        MowerCommand command = mowerCommandFactory.getCommand(MowerCommandType.MOVE);
        assertNotNull(command);
        assertInstanceOf(MoveCommand.class, command);
    }

    @Test
    void testGetRotateLeftCommand() {
        MowerCommand command = mowerCommandFactory.getCommand(MowerCommandType.LEFT);
        assertNotNull(command);
        assertInstanceOf(RotateLeftCommand.class, command);
    }

    @Test
    void testGetRotateRightCommand() {
        MowerCommand command = mowerCommandFactory.getCommand(MowerCommandType.RIGHT);
        assertNotNull(command);
        assertInstanceOf(RotateRightCommand.class, command);
    }

}
