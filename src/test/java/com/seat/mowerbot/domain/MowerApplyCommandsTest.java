package com.seat.mowerbot.domain;

import com.seat.mowerbot.domain.command.MowerCommand;
import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.domain.model.Plateau;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MowerApplyCommandsTest {

    @Mock
    private MowerCommand mowerCommand;

    @BeforeEach
    void setUp() {
    }

    @Test
    void applyCommand() {
        Mower mower = aMower();
        when(mowerCommand.execute(eq(mower))).thenReturn(mower);
        MowerApplyCommands mowerApplyCommands = new MowerApplyCommands(aMower());
        mowerApplyCommands.applyCommand(mowerCommand);
        assertEquals(mower, mowerApplyCommands.getMower());
    }

    private Mower aMower() {
        return new Mower(new Plateau(5, 5),
                new Location(3, 3),
                Cardinal.NORTH);
    }

}