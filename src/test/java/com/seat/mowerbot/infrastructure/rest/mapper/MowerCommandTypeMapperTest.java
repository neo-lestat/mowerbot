package com.seat.mowerbot.infrastructure.rest.mapper;

import com.seat.mowerbot.domain.command.MowerCommandException;
import com.seat.mowerbot.domain.command.MowerCommandType;
import com.tngtech.junit.dataprovider.DataProvider;
import com.tngtech.junit.dataprovider.UseDataProvider;
import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(UseDataProviderExtension.class)
class MowerCommandTypeMapperTest {

    MowerCommandTypeMapper mowerCommandTypeMapper;

    @BeforeEach
    void setUp() {
        mowerCommandTypeMapper = new MowerCommandTypeMapper();
    }

    @TestTemplate
    @UseDataProvider("dataProvider")
    void testMapStringToCommandList(String strCommands, List<MowerCommandType> mowerCommandTypeListExpected) {
        List<MowerCommandType> mowerCommandTypeList = mowerCommandTypeMapper.map(strCommands);
        assertEquals(mowerCommandTypeListExpected.size(), mowerCommandTypeList.size());
        for (int i = 0; i < mowerCommandTypeList.size(); i++) {
            assertEquals(mowerCommandTypeListExpected.get(i), mowerCommandTypeList.get(i));
        }
    }

    @Test
    void testMapThrowsCommandException() {
        String strCommands = "x";
        assertThrows(MowerCommandException.class, () -> {
            mowerCommandTypeMapper.map(strCommands);
        });
    }

    @DataProvider
    static Object[][] dataProvider() {
        return new Object[][] {
                {"MLr", Arrays.asList(MowerCommandType.MOVE, MowerCommandType.LEFT, MowerCommandType.RIGHT)},
                {"M", Arrays.asList(MowerCommandType.MOVE)}
        };
    }

}
