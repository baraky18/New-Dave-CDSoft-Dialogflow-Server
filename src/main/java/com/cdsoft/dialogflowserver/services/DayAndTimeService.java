package com.cdsoft.dialogflowserver.services;

import com.cdsoft.dialogflowserver.dtos.*;
import com.cdsoft.dialogflowserver.entities.ProductDetails;
import com.cdsoft.dialogflowserver.mappers.language.CategoryMapper;
import com.cdsoft.dialogflowserver.mappers.language.DayMapper;
import com.cdsoft.dialogflowserver.util.DayUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.cdsoft.dialogflowserver.util.Constants.*;
import static com.cdsoft.dialogflowserver.util.DayUtil.TODAY;
import static com.cdsoft.dialogflowserver.util.DialogflowParams.BUSINESS_CALL_REQUEST_DATE;
import static com.cdsoft.dialogflowserver.util.DialogflowParams.BUSINESS_CALL_REQUEST_DAY;
import static java.time.DayOfWeek.*;
import static java.time.temporal.TemporalAdjusters.next;

@Service
@RequiredArgsConstructor
@Slf4j
public class DayAndTimeService {

    private final DayMapper dayMapper;

    public WebhookResponseDto getDateFromDay(WebhookRequestDto webhookRequestDto) {
        log.info("DayAndTimeService.getDateFromDay");
        String day = getDayFromRequest(webhookRequestDto);
        return prepareWebhookResponse(day);
    }

    private LocalDate getDate(String day) {
        return switch (DayUtil.fromString(day)) {
            case TODAY -> LocalDate.now();
            case TOMORROW -> LocalDate.now().plusDays(1);
            case DAY_AFTER_TOMORROW -> LocalDate.now().plusDays(2);
            case NEXT_WEEK, SUNDAY -> LocalDate.now().with(next(SUNDAY));
            case MONDAY -> LocalDate.now().with(next(MONDAY));
            case TUESDAY -> LocalDate.now().with(next(TUESDAY));
            case WEDNESDAY -> LocalDate.now().with(next(WEDNESDAY));
            case THURSDAY -> LocalDate.now().with(next(THURSDAY));
            case FRIDAY -> LocalDate.now().with(next(FRIDAY));
            case SATURDAY -> LocalDate.now().with(next(SATURDAY));
        };
    }

    private String getDayFromRequest(WebhookRequestDto webhookRequestDto) {
        return webhookRequestDto.getSessionInfo().getParameters().get(BUSINESS_CALL_REQUEST_DAY);
    }

    private WebhookResponseDto prepareWebhookResponse(String day) {
        log.info("ProductService.prepareWebhookResponse");
        LocalDate date = getDate(day);
        String replyDay = dayMapper.map(DayUtil.fromString(day));
        ArrayList<String> reply = new ArrayList<>();
        reply.add(String.format(CALL_DATE_MSG, replyDay));
        MessageDto message = MessageDto.builder()
                .text(TextDto.builder()
                        .text(reply).build()).build();
        Map<String, String> params = new HashMap<>();
        params.put(BUSINESS_CALL_REQUEST_DATE, date.toString());
        ArrayList<MessageDto> messages = new ArrayList<>();
        messages.add(message);
        return WebhookResponseDto.builder()
                .fulfillmentResponseDto(FulfillmentResponseDto.builder()
                        .messages(messages).build())
                .sessionInfoDto(SessionInfoDto.builder().parameters(params).build()).build();
    }
}
