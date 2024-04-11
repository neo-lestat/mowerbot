package com.seat.mowerbot.domain.command;

import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RotateLeftCommandTest {

    Location location;

    @Test
    void testExecute() {
        location = new Location(3, 3, Cardinal.EAST);
        Mower mower = new Mower(null, location);
        RotateLeftCommand rotateRightCommand = new RotateLeftCommand(mower);
        mower = rotateRightCommand.execute();
        Location expected = new Location(3, 3, Cardinal.NORTH);
        assertEquals(expected, mower.location());
    }

}
