package com.ecocow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductionCompanyDTO {
	private int id;
	private String logo_path;
	private String name;
	private String origin_country;
}
