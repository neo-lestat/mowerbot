package com.seat.mowerbot.application.service;

import com.seat.mowerbot.application.service.command.MowerCommandException;
import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.domain.model.MowerCommandType;
import com.seat.mowerbot.domain.model.Plateau;
import com.seat.mowerbot.application.service.command.MowerCommandFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static com.seat.mowerbot.domain.model.MowerCommandType.LEFT;
import static com.seat.mowerbot.domain.model.MowerCommandType.MOVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EvaluateMowerCommandsServiceImplTest {

    @Mock
    private MowerCommandFactory mowerCommandFactory;

    @InjectMocks
    private EvaluateMowerCommandsServiceImpl mowerService;

    @Test
    void testEvaluateCommandsValid() {
        Location locationInit = new Location(1, 2, Cardinal.NORTH);
        Location locationMiddle = new Location(1, 2, Cardinal.WEST);
        Location locationFinal = new Location(0, 2, Cardinal.WEST);
        when(mowerCommandFactory.getCommand(locationInit, LEFT)).thenReturn(() -> locationMiddle);
        when(mowerCommandFactory.getCommand(locationMiddle, MOVE)).thenReturn(() -> locationFinal);
        Mower mower = buildMower();
        Location result = mowerService.evaluateCommands(mower);
        assertEquals(locationFinal, result);
    }

    @Test
    void testEvaluateCommandsThrowsException() {
        Mower mower = buildMower();
        when(mowerCommandFactory.getCommand(mower.location(), LEFT))
                .thenThrow(new MowerCommandException("Error with location : " + mower.location()));
        assertThrows(MowerCommandException.class, () -> {
            mowerService.evaluateCommands(mower);
        });
    }

    private Mower buildMower() {
        Plateau plateau = new Plateau(5, 5);
        Location start = new Location(1, 2, Cardinal.NORTH);
        List<MowerCommandType> commands = Arrays.asList(LEFT, MOVE);
        return new Mower(plateau, start, commands);
    }
}
