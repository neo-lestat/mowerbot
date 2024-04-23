package com.seat.mowerbot.application.service;

import com.seat.mowerbot.domain.ApplyMowerCommandsUseCase;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MowerCommandsEvaluatorServiceImplTest {

    @Mock
    private ApplyMowerCommandsUseCase applyMowerCommandsUseCase;

    @InjectMocks
    private MowerCommandsEvaluatorServiceImpl mowerCommandsEvaluatorServiceImpl;


    @Test
    void testEvaluateCommands() {
        Mower mower = aMower();
        Mower mowerAppliedCommands = new Mower.Builder().from(mower)
                .withLocation(new Location(2, 2));
        List<MowerCommandType> commandTypeList = Arrays.asList(LEFT, MOVE);
        when(applyMowerCommandsUseCase.applyCommands(mower, commandTypeList))
                .thenReturn(mowerAppliedCommands);
        Mower result = mowerCommandsEvaluatorServiceImpl.evaluateCommands(mower, commandTypeList);
        assertEquals(mowerAppliedCommands, result);

    }

    private Mower aMower() {
        Plateau plateau = new Plateau(5, 5);
        Location start = new Location(1, 2);
        return new Mower(plateau, start, Cardinal.NORTH);
    }
}
