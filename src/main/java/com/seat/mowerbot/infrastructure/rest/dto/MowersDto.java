package com.seat.mowerbot.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class MowersDto {

    @JsonProperty("plateau")
    @NotNull
    private PlateauDto plateauDto;
    @NotNull
    @Valid
    private List<MowerDto> mowers;

    public PlateauDto getPlateauRequest() {
        return plateauDto;
    }

    public void setPlateauRequest(PlateauDto plateauDto) {
        this.plateauDto = plateauDto;
    }

    public List<MowerDto> getMowers() {
        return mowers;
    }

    public void setMowers(List<MowerDto> mowers) {
        this.mowers = mowers;
    }
}
