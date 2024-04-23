package com.seat.mowerbot.infrastructure.rest.mapper;

import com.seat.mowerbot.domain.model.Cardinal;
import com.tngtech.junit.dataprovider.DataProvider;
import com.tngtech.junit.dataprovider.UseDataProvider;
import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(UseDataProviderExtension.class)
class CardinalMapperTest {

    private CardinalMapper cardinalMapper;

    @BeforeEach
    void setUp() {
        cardinalMapper = new CardinalMapper();
    }

    @TestTemplate
    @UseDataProvider("dataProvider")
    void map(String shortLetter, Cardinal cardinal) {
        Cardinal result = cardinalMapper.map(shortLetter);
        assertEquals(cardinal, result);
    }

    @DataProvider
    static Object[][] dataProvider() {
        return new Object[][] {
                {"N", Cardinal.NORTH},
                {"S", Cardinal.SOUTH},
                {"E", Cardinal.EAST},
                {"W", Cardinal.WEST}
        };
    }
}
