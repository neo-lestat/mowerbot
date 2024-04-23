package com.seat.mowerbot.application.service;

import com.seat.mowerbot.domain.ApplyMowerCommandsUseCase;
import com.seat.mowerbot.domain.command.MowerCommandType;
import com.seat.mowerbot.domain.model.Mower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MowerCommandsEvaluatorServiceImpl implements MowerCommandsEvaluatorService {

    private final ApplyMowerCommandsUseCase applyMowerCommandsUseCase;

    @Autowired
    public MowerCommandsEvaluatorServiceImpl(ApplyMowerCommandsUseCase applyMowerCommandsUseCase) {
        this.applyMowerCommandsUseCase = applyMowerCommandsUseCase;
    }

    public Mower evaluateCommands(Mower mower, List<MowerCommandType> mowerCommandTypeList) {
        return applyMowerCommandsUseCase.applyCommands(mower, mowerCommandTypeList);
    }
}
