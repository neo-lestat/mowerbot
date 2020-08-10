package com.seat.mowerbot.application.service;

import com.seat.mowerbot.domain.Cardinal;
import com.seat.mowerbot.domain.Location;
import com.seat.mowerbot.domain.MowerCommandType;
import com.seat.mowerbot.domain.Plateau;
import com.seat.mowerbot.application.service.command.MowerCommandException;
import com.seat.mowerbot.application.service.command.MowerCommandFactory;
import com.seat.mowerbot.infrastructure.rest.request.StringToMowerCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
* This test has real objects instead mocks, this could not be ok for more complex scenarios
* */
class MowerServiceImplTest {

    private MowerCommandFactory mowerCommandFactory;
    private StringToMowerCommandMapper stringToMowerCommandMapper;
    private MowerService mowerService;

    @BeforeEach
    void setUp() {
        mowerCommandFactory = new MowerCommandFactory();
        stringToMowerCommandMapper = new StringToMowerCommandMapper();
        mowerService = new MowerServiceImpl(mowerCommandFactory);
    }

    @Test
    void testCaseOne() throws MowerCommandException {
        Plateau plateau = new Plateau(5,5);
        Location start = new Location(1,2, Cardinal.NORTH);
        List <MowerCommandType>commands = stringToMowerCommandMapper.map( "LMLMLMLMM");
        Location result = mowerService.evaluateCommands(plateau, start, commands);
        Location expected = new Location(1, 3, Cardinal.NORTH);
        assertEquals(expected, result);
    }

    @Test
    void testCaseTwo() throws MowerCommandException {
        Plateau plateau = new Plateau(5,5);
        Location start = new Location(3,3, Cardinal.EAST);
        List <MowerCommandType>commands = stringToMowerCommandMapper.map("MMRMMRMRRM");
        Location result = mowerService.evaluateCommands(plateau, start, commands);
        Location expected = new Location(5, 1, Cardinal.EAST);
        assertEquals(expected, result);
    }

    @Test
    void testGetThrowsException()  {
        Plateau plateau = new Plateau(5,5);
        Location start = new Location(3, 0, Cardinal.SOUTH);
        assertThrows(MowerCommandException.class, () -> {
            mowerService.evaluateCommands(plateau, start, Collections.singletonList(MowerCommandType.UNKNOWN));
        });
    }

}
