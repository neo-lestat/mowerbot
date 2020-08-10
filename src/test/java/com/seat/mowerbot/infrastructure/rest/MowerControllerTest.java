package com.seat.mowerbot.infrastructure.rest;

import com.seat.mowerbot.application.service.MowerService;
import com.seat.mowerbot.domain.Cardinal;
import com.seat.mowerbot.domain.Location;
import com.seat.mowerbot.domain.MowerCommandType;
import com.seat.mowerbot.domain.Plateau;
import com.seat.mowerbot.infrastructure.rest.request.MowerData;
import com.seat.mowerbot.infrastructure.rest.request.MowerRequest;
import com.seat.mowerbot.application.service.command.MowerCommandException;
import com.seat.mowerbot.infrastructure.rest.request.StringToMowerCommandMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MowerControllerTest {

    @Mock
    private StringToMowerCommandMapper stringToMowerCommandMapper;

    @Mock
    private MowerService mowerService;

    @InjectMocks
    private MowerController mowerController;

    @Test
    void ping() {
        String pong = mowerController.ping();
        assertEquals("pong", pong);
    }

    @Test
    void evaluateCommands() throws MowerCommandException {
        Plateau plateau = new Plateau(5,5);
        Location initLocation = new Location(1,2, Cardinal.NORTH);
        String movements = "LM";
        MowerRequest mowerRequest = buildMowerRequest(plateau, initLocation, movements);
        Location finalLocation = new Location(  0, 2, Cardinal.WEST);
        List<MowerCommandType> commands = Arrays.asList(MowerCommandType.LEFT, MowerCommandType.MOVE);
        when(stringToMowerCommandMapper.map(movements)).thenReturn(commands);
        when(mowerService.evaluateCommands(plateau, initLocation, commands))
                .thenReturn(finalLocation);
        List<Location> results = mowerController.evaluateCommands(mowerRequest);
        assertEquals(1, results.size());
        assertEquals(finalLocation, results.get(0));
    }

    private MowerRequest buildMowerRequest(Plateau plateau, Location initLocation, String movements){
        MowerData mowerData = new MowerData();
        mowerData.setLocation(initLocation);
        mowerData.setCommands(movements);
        MowerRequest mowerRequest = new MowerRequest();
        mowerRequest.setPlateau(plateau);
        mowerRequest.setMowerDataList(Collections.singletonList(mowerData));
        return mowerRequest;
    }

    @Test
    void evaluateCommandsThrowsException() throws MowerCommandException {
        Plateau plateau = new Plateau(5,5);
        Location initLocation = new Location(1,2, Cardinal.NORTH);
        String movements = "LM";
        MowerRequest mowerRequest = buildMowerRequest(plateau, initLocation, movements);
        List<MowerCommandType> commands = Arrays.asList(MowerCommandType.LEFT, MowerCommandType.MOVE);
        when(stringToMowerCommandMapper.map(movements)).thenReturn(commands);
        when(mowerService.evaluateCommands(plateau, initLocation, commands))
                .thenThrow(new MowerCommandException("test"));
        assertThrows(ResponseStatusException.class, () -> {
            mowerController.evaluateCommands(mowerRequest);
        });
    }
}