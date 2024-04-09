package com.seat.mowerbot.infrastructure.rest.mapper;

import com.seat.mowerbot.application.service.command.MowerCommandException;
import com.seat.mowerbot.domain.model.MowerCommandType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MowerCommandMapper {

    public List<MowerCommandType> map(String commands) {
        return commands.chars()
                .mapToObj(this::getMowerCommandType)
                .collect(Collectors.toList());
    }

    private MowerCommandType getMowerCommandType(int shortLetter) {
        char shortLetterChar = Character.toUpperCase((char) shortLetter);
        return Arrays.stream(MowerCommandType.values())
                .filter(value -> value.getShortLetter() == shortLetterChar)
                .findAny().orElseThrow(() -> new MowerCommandException("Wrong command type: " + shortLetterChar));
    }

}
