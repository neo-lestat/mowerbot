package com.seat.mowerbot.domain.command;

import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.domain.model.Plateau;
import com.tngtech.junit.dataprovider.DataProvider;
import com.tngtech.junit.dataprovider.UseDataProvider;
import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(UseDataProviderExtension.class)
class MoveCommandTest {

    @TestTemplate
    @UseDataProvider("dataProvider")
    void testExecute(Cardinal cardinal, Location location) {
        Mower mower = aMower(cardinal);
        Mower expected = new Mower.Builder()
                .from(mower).withLocation(location);
        MoveCommand moveCommand = new MoveCommand();
        mower = moveCommand.execute(mower);
        assertEquals(expected, mower);
    }


    @Test
    void testExecuteWithNewLocationNotValid() {
        Mower mower = new Mower.Builder()
                .from(aMower(Cardinal.NORTH)).withLocation(new Location(3, 5));
        MoveCommand moveCommand = new MoveCommand();
        MowerCommandException thrown = assertThrows(
                MowerCommandException.class,
                () -> moveCommand.execute(mower),
                "Expected doThing() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Error invalid Location[x=3, y=6]"));
    }

    private Mower aMower(Cardinal cardinal) {
        return new Mower(new Plateau(5, 5),
                new Location(3, 3),
                cardinal);
    }

    @DataProvider
    static Object[][] dataProvider() {
        return new Object[][] {
                {Cardinal.NORTH, new Location(3, 4)},
                {Cardinal.SOUTH, new Location(3, 2)},
                {Cardinal.EAST, new Location(4, 3)},
                {Cardinal.WEST, new Location(2, 3)}
        };
    }

}
