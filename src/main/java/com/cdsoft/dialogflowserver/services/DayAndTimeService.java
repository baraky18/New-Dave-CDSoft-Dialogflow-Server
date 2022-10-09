package com.cdsoft.dialogflowserver.services;

import com.cdsoft.dialogflowserver.dtos.WebhookRequestDto;
import com.cdsoft.dialogflowserver.dtos.WebhookResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DayAndTimeService {

    public WebhookResponseDto getDateFromDay(WebhookRequestDto webhookRequestDto) {
        log.info("DayAndTimeService.getDateFromDay");
        return null;
    }
}
