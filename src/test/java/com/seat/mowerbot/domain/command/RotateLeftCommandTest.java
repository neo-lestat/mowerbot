package com.seat.mowerbot.domain.command;

import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.domain.model.Plateau;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RotateLeftCommandTest {

    @Test
    void testExecute() {
        Mower mower = aMower();
        Mower expected = new Mower.Builder().from(mower).withDirection(Cardinal.WEST);
        RotateLeftCommand rotateLeftCommand = new RotateLeftCommand();
        mower = rotateLeftCommand.execute(mower);
        assertEquals(expected, mower);
    }

    private Mower aMower() {
        return new Mower(new Plateau(5, 5),
                new Location(3, 3),
                Cardinal.NORTH);
    }

}
