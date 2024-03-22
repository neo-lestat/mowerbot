package com.seat.mowerbot.infrastructure.rest.request;

import com.seat.mowerbot.domain.Plateau;
import org.springframework.stereotype.Component;

@Component
public class PlateauMapper {

    public Plateau map(PlateauRequest plateauRequest) {
        return new Plateau(plateauRequest.width(), plateauRequest.height());
    }
}
