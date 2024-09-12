package com.ecocow.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CollectionDTO {
	private int id;
	private String name;
	private String poster_path;
	private String backdrop_path;
}
