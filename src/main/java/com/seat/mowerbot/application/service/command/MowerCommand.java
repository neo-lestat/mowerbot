package com.seat.mowerbot.application.service.command;

import com.seat.mowerbot.domain.Location;

public interface MowerCommand {

    Location execute();

}
