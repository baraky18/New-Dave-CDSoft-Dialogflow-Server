package com.cdsoft.dialogflowserver.dtos.integrator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductsDetailsDto {

    private List<ProductDetailsDto> productsDetailsDto;
}
