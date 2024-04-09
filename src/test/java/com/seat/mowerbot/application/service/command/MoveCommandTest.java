package com.seat.mowerbot.application.service.command;

import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveCommandTest {

    private Location location;

    @Test
    void testExecute() {
        location = new Location(3, 3, Cardinal.EAST);
        MoveCommand moveCommand = new MoveCommand(location);
        Location actual = moveCommand.execute();
        Location expected = new Location(4, 3, Cardinal.EAST);
        assertEquals(expected, actual);
    }

}
