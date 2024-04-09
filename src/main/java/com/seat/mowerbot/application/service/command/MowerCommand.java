package com.seat.mowerbot.application.service.command;

import com.seat.mowerbot.domain.model.Location;

public interface MowerCommand {

    Location execute();

}
