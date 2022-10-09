package com.cdsoft.dialogflowserver.mappers.language;

import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public String map(String category){
        return switch (category) {
            case "מסכי מחשב" -> "מסך מחשב";
            case "מחשבים נייחים" -> "מחשב נייח";
            case "מחשבים שולחניים" -> "מחשב שולחני";
            case "מדפסות וסורקים" -> "מדפסת/ סורק";
            case "שרתים וגיבוי" -> "שרת";
            case "תוכנות ורישוי תוכנה" -> "תוכנה";
            case "מחשבים ניידים וטאבלטים" -> "מחשב נייד";
            case "רכיבי מחשב" -> "רכיב מחשב";
            default -> category;
        };
    }

}
