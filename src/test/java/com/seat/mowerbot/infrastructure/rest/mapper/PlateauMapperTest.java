package com.seat.mowerbot.infrastructure.rest.mapper;

import com.seat.mowerbot.domain.model.Plateau;
import com.seat.mowerbot.infrastructure.rest.dto.PlateauDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlateauMapperTest {

    PlateauMapper plateauMapper;

    @BeforeEach
    void setUp() {
        plateauMapper = new PlateauMapper();
    }

    @Test
    void testMapDtoToDomain() {
        PlateauDto plateauDto = new PlateauDto(5, 5);
        Plateau plateau = plateauMapper.map(plateauDto);
        assertEquals(plateauDto.height(), plateau.height());
        assertEquals(plateauDto.width(), plateau.width());
    }
}
