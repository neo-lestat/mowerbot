package com.seat.mowerbot.infrastructure.rest.mapper;

import com.seat.mowerbot.domain.model.Plateau;
import com.seat.mowerbot.infrastructure.rest.dto.PlateauDto;
import org.springframework.stereotype.Component;

@Component
public class PlateauMapper {

    public Plateau map(PlateauDto plateauDto) {
        return new Plateau(plateauDto.width(), plateauDto.height());
    }
}
