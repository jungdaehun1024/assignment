package com.ecocow.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieDetailDto {
	private boolean adult;
	private String backdrop_path;
	private CollectionDTO belongs_to_collection;
	private int budget;
	private List<GenreDTO> genres;
	private String homepage;
	private int id;
	private String imdb_id;
	private List<String> origin_country;
	private String original_language;
	private String original_title;
	private String overview;
	private double popularity;
	private String poster_path;
	private List<ProductionCompanyDTO> production_companies; 
	private List<ProductionCountryDTO> production_countries; 
	private String release_date;
	private int revenue;
	private int runtime;
	private List<SpokenLanguageDTO> spoken_languages;
	private String status;
	private String tagline;
	private String title;
	private boolean video;
	private double vote_average;
	private int  vote_count;
}
