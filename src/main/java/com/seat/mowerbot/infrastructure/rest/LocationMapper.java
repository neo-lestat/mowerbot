package com.seat.mowerbot.infrastructure.rest;

import com.seat.mowerbot.domain.Cardinal;
import com.seat.mowerbot.domain.Location;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationMapper {

    public Location dtoToDomain(LocationDto source) {
        return new Location(source.getX(), source.getY(),
                Cardinal.getValueFromShortLetter(source.getDirection()));
    }

    public List<LocationDto> domainToDto(List<Location> source) {
        return source.stream()
                .map(this::domainToDto)
                .collect(Collectors.toList());
    }

    public LocationDto domainToDto(Location source) {
        return new LocationDto(source.getX(), source.getY(),
                source.getDirection().getShortLetter());
    }

}
