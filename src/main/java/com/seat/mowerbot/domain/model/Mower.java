package com.seat.mowerbot.domain.model;

import java.util.List;

public record Mower(Plateau plateau, Location location, List<MowerCommandType> commands){
}
