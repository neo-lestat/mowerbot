package com.seat.mowerbot.application.service;

import com.seat.mowerbot.domain.ApplyMowerCommandsUseCase;
import com.seat.mowerbot.domain.MowerApplyCommands;
import com.seat.mowerbot.domain.command.MowerCommandFactory;
import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.domain.command.MowerCommandType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplyMowerCommandsUseCaseImpl implements ApplyMowerCommandsUseCase {

    private final MowerCommandFactory mowerCommandFactory;

    @Autowired
    public ApplyMowerCommandsUseCaseImpl(MowerCommandFactory mowerCommandFactory) {
        this.mowerCommandFactory = mowerCommandFactory;
    }

    public Mower applyCommands(Mower mower, List<MowerCommandType> mowerCommandTypeList) {
        MowerApplyCommands mowerApplyCommands = new MowerApplyCommands(mower);
        mowerCommandTypeList.stream()
                .map(mowerCommandType -> mowerCommandFactory.getCommand(mowerCommandType))
                .forEach(mowerApplyCommands::applyCommand);
        return mowerApplyCommands.getMower();
    }

}
