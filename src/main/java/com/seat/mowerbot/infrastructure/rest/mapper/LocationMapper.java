package com.seat.mowerbot.infrastructure.rest.mapper;

import com.seat.mowerbot.domain.command.MowerCommandException;
import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.infrastructure.rest.dto.LocationDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class LocationMapper {

    public Location dtoToDomain(LocationDto source) {
        return new Location(source.x(), source.y(),
                getCardinal(source.direction()));
    }

    public LocationDto domainToDto(Location source) {
        return new LocationDto(source.x(), source.y(),
                source.direction().getShortLetter());
    }

    public Cardinal getCardinal(char shortLetter) {
        return Arrays.stream(Cardinal.values())
                .filter(cardinal -> cardinal.getShortLetter() == Character.toUpperCase(shortLetter))
                .findAny().orElseThrow(() -> new MowerCommandException("invalid cardinal: " + shortLetter));
    }

}
