package com.seat.mowerbot.application.service;

import com.seat.mowerbot.domain.MowerCommandType;
import com.seat.mowerbot.application.service.command.MowerCommandException;
import com.seat.mowerbot.domain.Location;
import com.seat.mowerbot.domain.Plateau;

import java.util.List;

public interface MowerService {

    Location evaluateCommands(Plateau plateau, Location startLocation, List<MowerCommandType> commands) throws MowerCommandException;

}
