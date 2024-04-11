package com.seat.mowerbot.infrastructure.rest.mapper;

import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.infrastructure.rest.dto.MowerDto;
import com.seat.mowerbot.infrastructure.rest.dto.PlateauDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MowerMapper {

    private final PlateauMapper plateauMapper;
    private final LocationMapper locationMapper;

    @Autowired
    public MowerMapper(PlateauMapper plateauMapper,
                       LocationMapper locationMapper) {
        this.plateauMapper = plateauMapper;
        this.locationMapper = locationMapper;
    }

    public Mower mapToDomain(PlateauDto plateauDto, MowerDto mowerDto) {
        return new Mower(plateauMapper.map(plateauDto),
                locationMapper.dtoToDomain(mowerDto.getLocation()));

    }
}
