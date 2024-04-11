package com.seat.mowerbot.domain.command;


import com.seat.mowerbot.domain.model.Mower;

public interface MowerCommand {

    Mower execute();

}
