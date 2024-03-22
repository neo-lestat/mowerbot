package com.seat.mowerbot.infrastructure.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class MowerRequest {

    @JsonProperty("plateau")
    @NotNull
    private PlateauRequest plateauRequest;
    @NotNull
    @Valid
    private List<MowerData> mowerDataList;

    public PlateauRequest getPlateauRequest() {
        return plateauRequest;
    }

    public void setPlateauRequest(PlateauRequest plateauRequest) {
        this.plateauRequest = plateauRequest;
    }

    public List<MowerData> getMowerDataList() {
        return mowerDataList;
    }

    public void setMowerDataList(List<MowerData> mowerDataList) {
        this.mowerDataList = mowerDataList;
    }
}
