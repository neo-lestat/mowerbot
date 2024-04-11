package com.seat.mowerbot.domain.command;

import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RotateRightCommandTest {

    Location location;

    @Test
    void testExecute() {
        location = new Location(3, 3, Cardinal.EAST);
        Mower mower = new Mower(null, location);
        RotateRightCommand rotateRightCommand = new RotateRightCommand(mower);
        mower =  rotateRightCommand.execute();
        Location expected = new Location(3, 3, Cardinal.SOUTH);
        assertEquals(expected, mower.location());
    }

}
