package com.seat.mowerbot.application.usecase;

import com.seat.mowerbot.domain.command.MowerCommand;
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
class ApplyMowerCommandsUseCaseTest {

    @Mock
    private MowerCommandFactory mowerCommandFactory;

    @Mock
    private MowerCommand mowerCommand;

    @InjectMocks
    private ApplyMowerCommandsUseCaseImpl applyMowerCommandsUseCase;

    @Test
    void testExecuteCommandsValid() {
        Mower mowerInit = new Mower(new Plateau(5, 5), new Location(1, 2), Cardinal.NORTH);
        Mower mowerMiddle = new Mower.Builder().from(mowerInit).withDirection(Cardinal.WEST);
        Mower mowerFinal = new Mower.Builder().from(mowerMiddle).withLocation(new Location(0, 2));
        when(mowerCommandFactory.getCommand(LEFT)).thenReturn(mowerCommand);
        when(mowerCommandFactory.getCommand(MOVE)).thenReturn(mowerCommand);
        when(mowerCommand.execute(mowerInit)).thenReturn(mowerMiddle);
        when(mowerCommand.execute(mowerMiddle)).thenReturn(mowerFinal);
        Mower mower = buildMower();
        List<MowerCommandType> commandTypeList = Arrays.asList(LEFT, MOVE);
        Mower result = applyMowerCommandsUseCase.applyCommands(mower, commandTypeList);
        assertEquals(mowerFinal, result);
    }

    @Test
    void testExecuteCommandsThrowsException() {
        Mower mower = buildMower();
        List<MowerCommandType> commandTypeList = Arrays.asList(LEFT);
        when(mowerCommandFactory.getCommand(LEFT))
                .thenThrow(new MowerCommandException("Error with location : " + mower.location()));
        assertThrows(MowerCommandException.class, () -> {
            applyMowerCommandsUseCase.applyCommands(mower, commandTypeList);
        });
    }

    private Mower buildMower() {
        Plateau plateau = new Plateau(5, 5);
        Location start = new Location(1, 2);
        return new Mower(plateau, start, Cardinal.NORTH);
    }
}
