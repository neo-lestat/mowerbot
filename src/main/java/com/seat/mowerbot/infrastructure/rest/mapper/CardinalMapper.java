package com.seat.mowerbot.infrastructure.rest.mapper;

import com.seat.mowerbot.domain.command.MowerCommandException;
import com.seat.mowerbot.domain.model.Cardinal;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CardinalMapper {

    private static final Map<Character, Cardinal> cardinalByCharMap;

    static {
        cardinalByCharMap = new HashMap<>();
        cardinalByCharMap.put(Cardinal.NORTH.getShortLetter(), Cardinal.NORTH);
        cardinalByCharMap.put(Cardinal.SOUTH.getShortLetter(), Cardinal.SOUTH);
        cardinalByCharMap.put(Cardinal.EAST.getShortLetter(), Cardinal.EAST);
        cardinalByCharMap.put(Cardinal.WEST.getShortLetter(), Cardinal.WEST);
    }

    public Cardinal map(String shortLetter) {
        Optional<Cardinal> cardinal = Optional.ofNullable(cardinalByCharMap.get(shortLetter.charAt(0)));
        return cardinal.orElseThrow(() -> new MowerCommandException("invalid cardinal: " + shortLetter));
    }

    /* another approach
    public Cardinal getCardinal(char shortLetter) {
        return Arrays.stream(Cardinal.values())
                .filter(cardinal -> cardinal.getShortLetter() == Character.toUpperCase(shortLetter))
                .findAny().orElseThrow(() -> new MowerCommandException("invalid cardinal: " + shortLetter));
    }*/

}
