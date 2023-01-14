package com.cdsoft.dialogflowserver.services;

import com.cdsoft.dialogflowserver.dtos.google.*;
import com.cdsoft.dialogflowserver.enums.DayUtil;
import com.cdsoft.dialogflowserver.mappers.language.DayMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.cdsoft.dialogflowserver.util.Constants.CALL_DATE_MSG;
import static com.cdsoft.dialogflowserver.util.DialogflowEntities.*;
import static java.time.DayOfWeek.*;
import static java.time.temporal.TemporalAdjusters.next;

@Service
@RequiredArgsConstructor
@Slf4j
public class DayAndTimeService {

    private final DayMapper dayMapper;

    public LocalDateTime getRequestedDateAndTime(WebhookRequestDto webhookRequestDto) {
        log.info("DayAndTimeService.getRequestedDateAndTime");
        String day = webhookRequestDto.getSessionInfo().getParameters().get(DAY_ENTITY);
        int hour = Integer.parseInt(webhookRequestDto.getSessionInfo().getParameters().get(HOUR_ENTITY));
        String minuteString = webhookRequestDto.getSessionInfo().getParameters().get(MINUTE_ENTITY);
        int minute = minuteString != null ? Integer.parseInt(minuteString) : 0;
        return getDateAndTime(day, hour, minute);
    }

    public WebhookResponseDto prepareWebhookResponse(WebhookRequestDto webhookRequestDto) {
        log.info("DayAndTimeService.prepareWebhookResponse");
        LocalDateTime date = this.getRequestedDateAndTime(webhookRequestDto);
        String day = webhookRequestDto.getSessionInfo().getParameters().get(DAY_ENTITY);
        String replyDay = dayMapper.map(DayUtil.fromString(day));
        ArrayList<String> reply = new ArrayList<>();
        reply.add(String.format(CALL_DATE_MSG, replyDay));
        MessageDto message = MessageDto.builder()
                .text(TextDto.builder()
                        .text(reply).build()).build();
//        Map<String, String> params = new HashMap<>();
//        params.put(DATE_ENTITY, date.toString());
        ArrayList<MessageDto> messages = new ArrayList<>();
        messages.add(message);
        return WebhookResponseDto.builder()
                .fulfillmentResponseDto(FulfillmentResponseDto.builder()
                        .messages(messages).build())
                .sessionInfoDto(webhookRequestDto.getSessionInfo())
                .targetPage("projects/cdsoft-faq/locations/europe-west3/agents/d1b1313d-acb8-401b-9a15-92ed13475568/flows/d8cf5da9-2e7b-42a4-ac26-30d937efa35f/pages/627a0ddd-73ae-4d14-a5e0-8264a153cc79")
                .build();
    }

    private LocalDateTime getDateAndTime(String day, int hour, int minute) {
        return switch (DayUtil.fromString(day)) {
            case TODAY -> LocalDate.now().atTime(hour, minute);
            case TOMORROW -> LocalDate.now().plusDays(1).atTime(hour, minute);
            case DAY_AFTER_TOMORROW -> LocalDate.now().plusDays(2).atTime(hour, minute);
            case NEXT_WEEK, SUNDAY -> LocalDate.now().atTime(hour, minute).with(next(SUNDAY));
            case MONDAY -> LocalDate.now().atTime(hour, minute).with(next(MONDAY));
            case TUESDAY -> LocalDate.now().atTime(hour, minute).with(next(TUESDAY));
            case WEDNESDAY -> LocalDate.now().atTime(hour, minute).with(next(WEDNESDAY));
            case THURSDAY -> LocalDate.now().atTime(hour, minute).with(next(THURSDAY));
            case FRIDAY -> LocalDate.now().atTime(hour, minute).with(next(FRIDAY));
            case SATURDAY -> LocalDate.now().atTime(hour, minute).with(next(SATURDAY));
        };
    }
}
