package com.seat.mowerbot.infrastructure.rest;

import com.seat.mowerbot.application.service.command.MowerCommandException;
import com.seat.mowerbot.domain.Cardinal;
import com.seat.mowerbot.domain.Location;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class LocationDtoMapper {

    public Location dtoToDomain(LocationDto source) {
        return new Location(source.x(), source.y(),
                getValueFromShortLetter(source.direction()));
    }

    public LocationDto domainToDto(Location source) {
        return new LocationDto(source.x(), source.y(),
                source.direction().getShortLetter());
    }

    public Cardinal getValueFromShortLetter(char shortLetter) {
        return Arrays.stream(Cardinal.values())
                .filter(cardinal -> cardinal.getShortLetter() == Character.toUpperCase(shortLetter))
                .findAny().orElseThrow(() -> new MowerCommandException("invalid cardinal: " + shortLetter));
    }

}
