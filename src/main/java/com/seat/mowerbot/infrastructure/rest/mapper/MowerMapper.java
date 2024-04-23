package com.seat.mowerbot.infrastructure.rest.mapper;

import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.infrastructure.rest.dto.MowerDto;
import com.seat.mowerbot.infrastructure.rest.dto.MowerResponseDto;
import com.seat.mowerbot.infrastructure.rest.dto.PlateauDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MowerMapper {

    private final PlateauMapper plateauMapper;
    private final LocationMapper locationMapper;
    private final CardinalMapper cardinalMapper;

    @Autowired
    public MowerMapper(PlateauMapper plateauMapper,
                       LocationMapper locationMapper,
                       CardinalMapper cardinalMapper) {
        this.plateauMapper = plateauMapper;
        this.locationMapper = locationMapper;
        this.cardinalMapper = cardinalMapper;
    }

    public Mower dtoToDomain(PlateauDto plateauDto, MowerDto mowerDto) {
        return new Mower(plateauMapper.map(plateauDto),
                locationMapper.dtoToDomain(mowerDto.getLocation()),
                cardinalMapper.map(mowerDto.getDirection()));
    }

    public MowerResponseDto domainToDto(Mower mower) {
        return new MowerResponseDto(
                locationMapper.domainToDto(mower.location()),
                String.valueOf(mower.direction().getShortLetter()));
    }
}
