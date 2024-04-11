package com.seat.mowerbot.application.service;

import com.seat.mowerbot.domain.command.MowerCommandFactory;
import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.domain.command.MowerCommandType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MowerCommandsExecutor {

    private final MowerCommandFactory mowerCommandFactory;

    @Autowired
    public MowerCommandsExecutor(MowerCommandFactory mowerCommandFactory) {
        this.mowerCommandFactory = mowerCommandFactory;
    }

    public Mower executeCommands(Mower mower, List<MowerCommandType> mowerCommandTypeList) {
        for (MowerCommandType mowerCommandType : mowerCommandTypeList) {
            mower = mowerCommandFactory.getCommand(mower, mowerCommandType).execute();
        }
        return mower;
    }

}
