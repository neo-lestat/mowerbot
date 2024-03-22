package com.seat.mowerbot.application.service;

import com.seat.mowerbot.domain.Cardinal;
import com.seat.mowerbot.domain.Location;
import com.seat.mowerbot.domain.MowerCommandType;
import com.seat.mowerbot.domain.Plateau;
import com.seat.mowerbot.application.service.command.MowerCommandException;
import com.seat.mowerbot.application.service.command.MowerCommandFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.seat.mowerbot.domain.MowerCommandType.LEFT;
import static com.seat.mowerbot.domain.MowerCommandType.MOVE;
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
    void testEvaluateCommandsValid() throws MowerCommandException {
        when(mowerCommandFactory.getCommand(new Location(1, 2, Cardinal.NORTH), LEFT))
                .thenReturn(() -> new Location(1, 2, Cardinal.WEST));
        when(mowerCommandFactory.getCommand(new Location(1, 2, Cardinal.WEST), MOVE))
                .thenReturn(() -> new Location(0, 2, Cardinal.WEST));
        Plateau plateau = new Plateau(5, 5);
        Location start = new Location(1, 2, Cardinal.NORTH);
        List<MowerCommandType> commands = Arrays.asList(LEFT, MOVE);
        Location result = mowerService.evaluateCommands(plateau, start, commands);
        Location expected = new Location(0, 2, Cardinal.WEST);
        assertEquals(expected, result);
    }

    @Test
    void testEvaluateCommandsThrowsException() throws MowerCommandException {
        Location start = new Location(3, 0, Cardinal.SOUTH);
        when(mowerCommandFactory.getCommand(start, LEFT))
                .thenThrow(new MowerCommandException("Error with location : " + start));
        Plateau plateau = new Plateau(5, 5);
        assertThrows(MowerCommandException.class, () -> {
            mowerService.evaluateCommands(plateau, start, Collections.singletonList(LEFT));
        });
    }

}
