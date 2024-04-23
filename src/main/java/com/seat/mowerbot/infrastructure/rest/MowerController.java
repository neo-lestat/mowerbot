package com.seat.mowerbot.infrastructure.rest;

import com.seat.mowerbot.application.service.MowerCommandsEvaluatorService;
import com.seat.mowerbot.domain.model.Mower;
import com.seat.mowerbot.domain.command.MowerCommandType;
import com.seat.mowerbot.infrastructure.rest.dto.MowerDto;
import com.seat.mowerbot.infrastructure.rest.dto.MowerResponseDto;
import com.seat.mowerbot.infrastructure.rest.dto.PlateauDto;
import com.seat.mowerbot.infrastructure.rest.mapper.MowerCommandTypeMapper;
import com.seat.mowerbot.infrastructure.rest.mapper.MowerMapper;
import com.seat.mowerbot.infrastructure.rest.dto.MowersDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/mower")
public class MowerController {

    private final MowerMapper mowerMapper;

    private final MowerCommandTypeMapper mowerCommandTypeMapper;

    private final MowerCommandsEvaluatorService mowerCommandsEvaluatorService;

    @Autowired
    public MowerController(MowerMapper mowerMapper,
                           MowerCommandTypeMapper mowerCommandTypeMapper,
                           MowerCommandsEvaluatorService mowerCommandsEvaluatorService) {
        this.mowerMapper = mowerMapper;
        this.mowerCommandTypeMapper = mowerCommandTypeMapper;
        this.mowerCommandsEvaluatorService = mowerCommandsEvaluatorService;
    }

    @Operation(summary = "Evaluate mower commands")
    @PostMapping("/commands")
    @ResponseStatus(HttpStatus.OK)
    public List<MowerResponseDto> evaluateCommands(@Valid @RequestBody MowersDto mowersDto) {
        PlateauDto plateauDto = mowersDto.getPlateauDto();
        return mowersDto.getMowers().stream()
                .map(mowerDto -> evaluateCommands(plateauDto, mowerDto))
                .map(mowerMapper::domainToDto)
                .collect(Collectors.toList());
    }

    private Mower evaluateCommands(PlateauDto plateauDto, MowerDto mowerDto) {
        Mower mower = mowerMapper.dtoToDomain(plateauDto, mowerDto);
        List<MowerCommandType> mowerCommands = mowerCommandTypeMapper.map(mowerDto.getCommands());
        mower = mowerCommandsEvaluatorService.evaluateCommands(mower, mowerCommands);
        return mower;
    }

}
