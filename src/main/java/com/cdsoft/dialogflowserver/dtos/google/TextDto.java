package com.cdsoft.dialogflowserver.dtos.google;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextDto {

    private List<String> text;
    private List<String> redactedText;
}
