package com.cdsoft.dialogflowserver.dtos.google;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionInfoDto {

    private String session;

    private Map<String, String> parameters;
}
