package com.seat.mowerbot.application.service;

import com.seat.mowerbot.domain.MowerCommandType;
import com.seat.mowerbot.domain.Location;
import com.seat.mowerbot.domain.Plateau;

import java.util.List;

public interface EvaluateMowerCommandsService {

    Location evaluateCommands(Plateau plateau, Location startLocation, List<MowerCommandType> commands);

}
