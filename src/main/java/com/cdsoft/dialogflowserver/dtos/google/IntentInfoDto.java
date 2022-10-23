package com.cdsoft.dialogflowserver.dtos.google;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IntentInfoDto {

    private String lastMatchedIntent;
    private String displayName;
    private double confidence;
}
