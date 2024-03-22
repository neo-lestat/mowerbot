package com.seat.mowerbot.infrastructure.rest.request;

import com.seat.mowerbot.domain.MowerCommandType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MowerCommandMapperTest {

    @Mock
    private MowerCommandTypeMapper mowerCommandTypeMapper;

    @InjectMocks
    private MowerCommandMapper mowerCommandMapper;

    @Test
    void testMap() {
        when(mowerCommandTypeMapper.getValue(eq('M'))).thenReturn(MowerCommandType.MOVE);
        when(mowerCommandTypeMapper.getValue(eq('L'))).thenReturn(MowerCommandType.LEFT);
        String strCommands = "ML";
        List<MowerCommandType> mowerCommandTypeList = mowerCommandMapper.map(strCommands);
        assertEquals(2, mowerCommandTypeList.size());
        assertEquals(MowerCommandType.MOVE, mowerCommandTypeList.get(0));
        assertEquals(MowerCommandType.LEFT, mowerCommandTypeList.get(1));
    }
}
