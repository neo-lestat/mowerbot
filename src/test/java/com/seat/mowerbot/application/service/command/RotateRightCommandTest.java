package com.seat.mowerbot.application.service.command;

import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RotateRightCommandTest {

    Location location;

    @Test
    void testExecute() {
        location = new Location(3, 3, Cardinal.EAST);
        RotateRightCommand rotateRightCommand = new RotateRightCommand(location);
        Location actual = rotateRightCommand.execute();
        Location expected = new Location(3, 3, Cardinal.SOUTH);
        assertEquals(expected, actual);
    }

}
