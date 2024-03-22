package com.seat.mowerbot.infrastructure.rest.request;

import com.seat.mowerbot.domain.MowerCommandType;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MowerCommandTypeMapper {
    public MowerCommandType getValue(char shortLetter) {
        return Arrays.stream(MowerCommandType.values())
                .filter(value -> value.getShortLetter() == Character.toUpperCase(shortLetter))
                .findAny().orElse(MowerCommandType.UNKNOWN);
    }
}
