package com.seat.mowerbot.infrastructure.rest;

import com.seat.mowerbot.application.service.command.MowerCommandException;
import com.seat.mowerbot.domain.Cardinal;
import com.seat.mowerbot.domain.Location;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationDtoMapper {

    public Location dtoToDomain(LocationDto source) throws MowerCommandException {
        return new Location(source.x(), source.y(),
                getValueFromShortLetter(source.direction()));
    }

    public List<LocationDto> domainToDto(List<Location> source) {
        return source.stream()
                .map(this::domainToDto)
                .collect(Collectors.toList());
    }

    public LocationDto domainToDto(Location source) {
        return new LocationDto(source.x(), source.y(),
                source.direction().getShortLetter());
    }

    public Cardinal getValueFromShortLetter(char shortLetter) throws MowerCommandException {
        return Arrays.stream(Cardinal.values())
                .filter(cardinal -> cardinal.getShortLetter() == Character.toUpperCase(shortLetter))
                .findAny().orElseThrow(() -> new MowerCommandException("invalid cardinal: " + shortLetter));
    }

}
