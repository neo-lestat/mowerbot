package com.seat.mowerbot.infrastructure.rest.request;

import com.seat.mowerbot.domain.Plateau;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class MowerRequest {

    @NotNull
    private Plateau plateau;
    @NotNull
    @Valid
    private List<MowerData> mowerDataList;

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public List<MowerData> getMowerDataList() {
        return mowerDataList;
    }

    public void setMowerDataList(List<MowerData> mowerDataList) {
        this.mowerDataList = mowerDataList;
    }
}
