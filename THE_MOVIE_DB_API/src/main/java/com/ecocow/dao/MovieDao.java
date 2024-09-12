package com.ecocow.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieDao {

	int movieExists(int movieId);
	int insertMovie(Map<String, Object> parameters);
	String movieDetail(int movieId);
	
}
