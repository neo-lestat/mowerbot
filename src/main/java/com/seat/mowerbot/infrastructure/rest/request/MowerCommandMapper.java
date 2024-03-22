package com.seat.mowerbot.infrastructure.rest.request;

import com.seat.mowerbot.domain.MowerCommandType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MowerCommandMapper {

    private final MowerCommandTypeMapper mowerCommandTypeMapper;

    @Autowired
    public MowerCommandMapper(MowerCommandTypeMapper mowerCommandTypeMapper) {
        this.mowerCommandTypeMapper = mowerCommandTypeMapper;
    }

    public List<MowerCommandType> map(String commands) {
        return commands.chars()
                .mapToObj(commandChar -> mowerCommandTypeMapper.getValue((char) commandChar))
                .collect(Collectors.toList());
    }

}
