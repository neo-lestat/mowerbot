package com.seat.mowerbot.application.service;

import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.domain.model.Location;

public interface EvaluateMowerCommandsService {

    Location evaluateCommands(Mower mower);

}
