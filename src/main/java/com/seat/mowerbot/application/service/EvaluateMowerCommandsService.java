package com.seat.mowerbot.application.service;

import com.seat.mowerbot.domain.model.MowerCommandType;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Plateau;

import java.util.List;

public interface EvaluateMowerCommandsService {

    Location evaluateCommands(Plateau plateau, Location startLocation, List<MowerCommandType> commands);

}
