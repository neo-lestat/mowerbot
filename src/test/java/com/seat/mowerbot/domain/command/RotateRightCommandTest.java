package com.seat.mowerbot.domain.command;

import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.domain.model.Plateau;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RotateRightCommandTest {

    @Test
    void testExecute() {
        Mower mower = aMower();
        Mower expected = new Mower.Builder().from(mower).withDirection(Cardinal.EAST);
        RotateRightCommand rotateRightCommand = new RotateRightCommand();
        mower =  rotateRightCommand.execute(mower);
        assertEquals(expected, mower);
    }

    private Mower aMower() {
        return new Mower(new Plateau(5, 5),
                new Location(3, 3),
                Cardinal.NORTH);
    }
}
