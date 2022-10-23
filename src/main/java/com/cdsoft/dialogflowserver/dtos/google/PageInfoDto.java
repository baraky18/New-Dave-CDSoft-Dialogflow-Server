package com.cdsoft.dialogflowserver.dtos.google;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageInfoDto {

    private String currentPage;
    private String displayName;
}
