package com.cdsoft.dialogflowserver.dtos.google;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FulfillmentInfoDto {

    private String tag;
}
