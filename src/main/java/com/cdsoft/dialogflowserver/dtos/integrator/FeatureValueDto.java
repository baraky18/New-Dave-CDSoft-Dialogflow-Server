package com.cdsoft.dialogflowserver.dtos.integrator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeatureValueDto {

    private String featureName;
    private String featureValue;
}
