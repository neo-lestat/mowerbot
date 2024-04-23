package com.seat.mowerbot.domain;

import com.seat.mowerbot.domain.command.MowerCommandType;
import com.seat.mowerbot.domain.model.Mower;

import java.util.List;

public interface ApplyMowerCommandsUseCase {

    Mower applyCommands(Mower mower, List<MowerCommandType> mowerCommandTypeList);

}
