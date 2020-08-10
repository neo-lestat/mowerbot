package com.seat.mowerbot.application.service.command;

import com.seat.mowerbot.application.service.command.RotateLeftCommand;
import com.seat.mowerbot.domain.Cardinal;
import com.seat.mowerbot.domain.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RotateLeftCommandTest {

    Location location;

    @Test
    void testExecute() {
        location = new Location(3, 3, Cardinal.EAST);
        RotateLeftCommand rotateRightCommand = new RotateLeftCommand(location);
        Location actual = rotateRightCommand.execute();
        Location expected = new Location(3, 3, Cardinal.NORTH);
        assertEquals(expected, actual);
    }

}
