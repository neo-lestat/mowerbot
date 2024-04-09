package com.seat.mowerbot.infrastructure.rest.mapper;

import com.seat.mowerbot.application.service.command.MowerCommandException;
import com.seat.mowerbot.domain.model.MowerCommandType;
import com.tngtech.junit.dataprovider.DataProvider;
import com.tngtech.junit.dataprovider.UseDataProvider;
import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(UseDataProviderExtension.class)
class MowerCommandMapperTest {

    MowerCommandMapper mowerCommandMapper;

    @BeforeEach
    void setUp() {
        mowerCommandMapper = new MowerCommandMapper();
    }

    @TestTemplate
    @UseDataProvider("dataProvider")
    void testMapStringToCommandList(String strCommands, List<MowerCommandType> mowerCommandTypeListExpected) {
        List<MowerCommandType> mowerCommandTypeList = mowerCommandMapper.map(strCommands);
        assertEquals(mowerCommandTypeListExpected.size(), mowerCommandTypeList.size());
        for (int i = 0; i < mowerCommandTypeList.size(); i++) {
            assertEquals(mowerCommandTypeListExpected.get(i), mowerCommandTypeList.get(i));
        }
    }

    @Test
    void testMapThrowsCommandException() {
        String strCommands = "x";
        assertThrows(MowerCommandException.class, () -> {
            mowerCommandMapper.map(strCommands);
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
