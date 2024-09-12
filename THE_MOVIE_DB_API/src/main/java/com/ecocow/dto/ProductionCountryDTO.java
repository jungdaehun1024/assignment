package com.ecocow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductionCountryDTO {
	private String iso_3166_1;
	private String name;
}
