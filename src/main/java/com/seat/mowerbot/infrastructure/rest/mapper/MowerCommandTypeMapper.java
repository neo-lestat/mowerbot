package com.seat.mowerbot.infrastructure.rest.mapper;

import com.seat.mowerbot.domain.command.MowerCommandException;
import com.seat.mowerbot.domain.command.MowerCommandType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MowerCommandTypeMapper {

    private static Map<Character, MowerCommandType> mowerCommandTypeByCharMap;

    static {
        mowerCommandTypeByCharMap = new HashMap<>();
        mowerCommandTypeByCharMap.put(MowerCommandType.MOVE.getShortLetter(), MowerCommandType.MOVE);
        mowerCommandTypeByCharMap.put(MowerCommandType.LEFT.getShortLetter(), MowerCommandType.LEFT);
        mowerCommandTypeByCharMap.put(MowerCommandType.RIGHT.getShortLetter(), MowerCommandType.RIGHT);
    }

    public List<MowerCommandType> map(String commandsStr) {
        return commandsStr.chars()
                .mapToObj(this::getMowerCommandType)
                .collect(Collectors.toList());
    }

    public MowerCommandType getMowerCommandType(int shortLetter) {
        char shortLetterChar = Character.toUpperCase((char) shortLetter);
        Optional<MowerCommandType> mowerCommandType = Optional.ofNullable(mowerCommandTypeByCharMap.get(shortLetterChar));
        return mowerCommandType.orElseThrow(() -> new MowerCommandException("Wrong command type: " + shortLetterChar));
    }

}
