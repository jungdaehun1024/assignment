package com.ecocow.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.ecocow.dao.MovieDao;
import com.ecocow.dto.MovieDetailDto;
import com.ecocow.dto.MovieResponse;
import com.ecocow.dto.RecommendDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
@PropertySource("classpath:tmdb.properties")
public class MovieService {
	
	@Autowired
	private MovieDao movieDao;
	
	
	@Value("${url}")
	private String baseUrl;
	
	@Value("${token}")
	private String token;
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final OkHttpClient client = new  OkHttpClient();
	
	
	//영화 목록
	public MovieResponse movieList() throws IOException{
		//요청 URL
		String url =baseUrl+ "/movie/popular?language=ko&page=1";
		
		Request request = new Request.Builder()
									.url(url)
									.get()
									.addHeader("accept", "application/json")
									.addHeader("Authorization",  "Bearer " + token)
									.build();
		
		try(Response response =client.newCall(request).execute()){
			if(!response.isSuccessful()) {
				throw new IOException("error"+response);
			}
			String jsonResponse = response.body().string();
			
			return objectMapper.readValue(jsonResponse,MovieResponse.class);
		}
	}
	
	//영화 상세
	public MovieDetailDto movieDetail(int movieId) throws Exception{
		
	  //DB에 영화 정보가 없다면 TMDB api를 호출해서 값을 가져온다. + 해당 영화의 상세 값을 DB에 저장
	  if(movieDao.movieExists(movieId) == 2) {
		    
			String url =String.format( "https://api.themoviedb.org/3/movie/%d?language=ko", movieId); 
	 		
			Request request = new Request.Builder()
			  .url(url)
			  .get()
			  .addHeader("accept", "application/json")
			  .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzYjZiZmNiZDIzNDVhOTdhMzk4ZTY1ZTI0YTY0YjBmZSIsIm5iZiI6MTcyNTkzMjMyNi4yMzg4NzksInN1YiI6IjY2ZGZhMGE0MTg4MGU0MDNiMjI4ZDMwZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Iw7zjuBUw09NZ9-NV1mkKZQeNDktk01ZeAUa2OSpSvk")
			  .build();
			
			try(Response response = client.newCall(request).execute()){
				if(!response.isSuccessful()) {
					throw new Exception();
				}
				
				String jsonResponse = response.body().string(); // Json을 문자열로 
				MovieDetailDto result =objectMapper.readValue(jsonResponse,MovieDetailDto.class);  //문자열로 변환된 Json을 Dto에 매핑
				
				Map<String, Object> parameters = new HashMap<>();
				parameters.put("id", result.getId()); // 영화 ID
				parameters.put("movieDetailJson", jsonResponse); // JSON 문자열 직접 사용
				movieDao.insertMovie(parameters);
				
				System.out.println("DB에 정보가 없는경우:"+result);
				
				return  result;
			
			}
		  
	  }else {
		  //DB에 정보가 있는 경우
		 
		  String result = movieDao.movieDetail(movieId);
		  MovieDetailDto mappingResult = objectMapper.readValue(result, MovieDetailDto.class);
		  System.out.println("DB에 정보가 있는경우:"+mappingResult);
		  return mappingResult;
	  }
	}
	
	
	public RecommendDTO recommendMovie(int movieId)throws Exception{
		String url = String.format("https://api.themoviedb.org/3/movie/%d/recommendations?language=ko", movieId);

		Request request = new Request.Builder()
		  .url(url)
		  .get()
		  .addHeader("accept", "application/json")
		  .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzYjZiZmNiZDIzNDVhOTdhMzk4ZTY1ZTI0YTY0YjBmZSIsIm5iZiI6MTcyNTkzMjMyNi4yMzg4NzksInN1YiI6IjY2ZGZhMGE0MTg4MGU0MDNiMjI4ZDMwZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Iw7zjuBUw09NZ9-NV1mkKZQeNDktk01ZeAUa2OSpSvk")
		  .build();
		
		try(Response response = client.newCall(request).execute()){
			if(!response.isSuccessful()) {
				throw new Exception();
			}
			String jsonResponse = response.body().string();
			return objectMapper.readValue(jsonResponse, RecommendDTO.class);
			
		}
	}
	

	

}
