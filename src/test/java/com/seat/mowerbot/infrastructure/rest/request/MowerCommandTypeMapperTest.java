package com.seat.mowerbot.infrastructure.rest.request;

import com.seat.mowerbot.application.service.command.MowerCommandException;
import com.seat.mowerbot.domain.MowerCommandType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MowerCommandTypeMapperTest {

    @Test
    void testMap() throws MowerCommandException {
        MowerCommandTypeMapper mowerCommandTypeMapper = new MowerCommandTypeMapper();
        assertEquals(MowerCommandType.MOVE, mowerCommandTypeMapper.getValue('M'));
        assertEquals(MowerCommandType.LEFT, mowerCommandTypeMapper.getValue('L'));
        assertEquals(MowerCommandType.RIGHT, mowerCommandTypeMapper.getValue('R'));
        assertThrows(MowerCommandException.class, () -> {
            mowerCommandTypeMapper.getValue('x');
        });
    }

}
