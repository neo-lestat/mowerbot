package com.seat.mowerbot.infrastructure.rest.request;

import com.seat.mowerbot.domain.MowerCommandType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StringToMowerCommandMapper {

    public List<MowerCommandType> map(String commands) {
        char [] commandsCharArray = commands.toCharArray();
        List <MowerCommandType> mowerCommandTypeList = new ArrayList<>(commandsCharArray.length);
        for (char instruction : commandsCharArray) {
            mowerCommandTypeList.add(MowerCommandType.getValue(""+instruction));
        }
        return mowerCommandTypeList;
    }

}
