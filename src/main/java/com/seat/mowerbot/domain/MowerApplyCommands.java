package com.seat.mowerbot.domain;

import com.seat.mowerbot.domain.command.MowerCommand;
import com.seat.mowerbot.domain.model.Mower;

public class MowerApplyCommands {

    private Mower mower;

    public MowerApplyCommands(Mower mower) {
        this.mower = mower;
    }

    public Mower getMower() {
        return mower;
    }

    public void applyCommand(MowerCommand mowerCommand) {
        this.mower = mowerCommand.execute(this.mower);
    }
}
