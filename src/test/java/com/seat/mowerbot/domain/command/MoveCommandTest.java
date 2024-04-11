package com.seat.mowerbot.domain.command;

import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.domain.model.Plateau;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveCommandTest {

    private Location location;

    @Test
    void testExecute() {
        location = new Location(3, 3, Cardinal.EAST);
        Mower mower = new Mower(new Plateau(4, 4), location);
        MoveCommand moveCommand = new MoveCommand(mower);
        mower = moveCommand.execute();
        Location expected = new Location(4, 3, Cardinal.EAST);
        assertEquals(expected, mower.location());
    }

}
