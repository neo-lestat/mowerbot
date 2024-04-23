package com.seat.mowerbot.application.service;

import com.seat.mowerbot.domain.command.MowerCommandType;
import com.seat.mowerbot.domain.model.Mower;

import java.util.List;

public interface MowerCommandsEvaluatorService {

    Mower evaluateCommands(Mower mower, List<MowerCommandType> mowerCommandTypeList);

}
