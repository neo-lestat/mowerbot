package com.seat.mowerbot.infrastructure.rest.mapper;

import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.infrastructure.rest.dto.LocationDto;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public Location dtoToDomain(LocationDto source) {
        return new Location(source.x(), source.y());
    }

    public LocationDto domainToDto(Location source) {
        return new LocationDto(source.x(), source.y());
    }


}
