package com.seat.mowerbot.application.service;

import com.seat.mowerbot.domain.command.MowerCommandException;
import com.seat.mowerbot.domain.command.MowerCommandFactory;
import com.seat.mowerbot.domain.command.MowerCommandType;
import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.domain.model.Plateau;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static com.seat.mowerbot.domain.command.MowerCommandType.LEFT;
import static com.seat.mowerbot.domain.command.MowerCommandType.MOVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MowerCommandsExecutorTest {

    @Mock
    private MowerCommandFactory mowerCommandFactory;

    @InjectMocks
    private MowerCommandsExecutor mowerService;

    @Test
    void testExecuteCommandsValid() {
        Plateau plateau = new Plateau(5,5);
        Mower locationInit = new Mower(plateau, new Location(1, 2, Cardinal.NORTH));
        Mower locationMiddle = new Mower(plateau, new Location(1, 2, Cardinal.WEST));
        Mower locationFinal = new Mower(plateau, new Location(0, 2, Cardinal.WEST));
        when(mowerCommandFactory.getCommand(locationInit, LEFT)).thenReturn(() -> locationMiddle);
        when(mowerCommandFactory.getCommand(locationMiddle, MOVE)).thenReturn(() -> locationFinal);
        Mower mower = buildMower();
        List<MowerCommandType> commandTypeList = Arrays.asList(LEFT, MOVE);
        Mower result = mowerService.executeCommands(mower, commandTypeList);
        assertEquals(locationFinal, result);
    }

    @Test
    void testExecuteCommandsThrowsException() {
        Mower mower = buildMower();
        List<MowerCommandType> commandTypeList = Arrays.asList(LEFT);
        when(mowerCommandFactory.getCommand(mower, LEFT))
                .thenThrow(new MowerCommandException("Error with location : " + mower.location()));
        assertThrows(MowerCommandException.class, () -> {
            mowerService.executeCommands(mower, commandTypeList);
        });
    }

    private Mower buildMower() {
        Plateau plateau = new Plateau(5, 5);
        Location start = new Location(1, 2, Cardinal.NORTH);
        return new Mower(plateau, start);
    }
}
