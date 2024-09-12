package com.ecocow.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecommendDTO {
	private int page;
	private List<ResultsDTO> results;
	private int total_pages;
	private int total_results;
}
