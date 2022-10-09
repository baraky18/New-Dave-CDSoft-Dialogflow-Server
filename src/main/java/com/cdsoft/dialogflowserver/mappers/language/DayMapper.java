package com.cdsoft.dialogflowserver.mappers.language;

import com.cdsoft.dialogflowserver.util.DayUtil;
import org.springframework.stereotype.Component;

@Component
public class DayMapper {

    public String map(DayUtil day){
        return switch (day) {
            case TODAY -> "היום";
            case TOMORROW -> "מחר";
            case DAY_AFTER_TOMORROW -> "מחרתיים";
            case NEXT_WEEK  -> "בשבוע הבא ביום ראשון";
            case SUNDAY -> "ביום ראשון הבא";
            case MONDAY -> "ביום שני הבא";
            case TUESDAY -> "ביום שלישי הבא";
            case WEDNESDAY -> "ביום רביעי הבא";
            case THURSDAY -> "ביום חמישי הבא";
            case FRIDAY -> "ביום שישי הבא";
            case SATURDAY -> "ביום שבת הבא";
        };
    }
}
