package com.seat.mowerbot.infrastructure.rest.request;

import com.seat.mowerbot.domain.MowerCommandType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringToMowerCommandMapperTest {

    @Test
    void testMap() {
        String strCommands = "MLRx";
        StringToMowerCommandMapper stringToMowerCommandMapper = new StringToMowerCommandMapper();
        List<MowerCommandType> mowerCommandTypeList = stringToMowerCommandMapper.map(strCommands);
        assertEquals(MowerCommandType.MOVE, mowerCommandTypeList.get(0));
        assertEquals(MowerCommandType.LEFT, mowerCommandTypeList.get(1));
        assertEquals(MowerCommandType.RIGHT, mowerCommandTypeList.get(2));
        assertEquals(MowerCommandType.UNKNOWN, mowerCommandTypeList.get(3));
    }
}