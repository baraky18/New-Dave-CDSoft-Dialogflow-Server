package com.cdsoft.dialogflowserver.util;

public enum DayUtil {

    TODAY("today"),
    TOMORROW("tomorrow"),
    DAY_AFTER_TOMORROW("day after tomorrow"),
    NEXT_WEEK("next week"),
    SUNDAY("sunday"),
    MONDAY("monday"),
    TUESDAY("tuesday"),
    WEDNESDAY("wednesday"),
    THURSDAY("thursday"),
    FRIDAY("friday"),
    SATURDAY("saturday");

    private final String day;

    DayUtil(String day) {
        this.day = day;
    }

    public static DayUtil fromString(String requestedDay) {
        for (DayUtil day : DayUtil.values()) {
            if (day.day.equalsIgnoreCase(requestedDay)) {
                return day;
            }
        }
        return null;
    }
}
