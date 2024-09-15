package com.ecocow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecocow.service.MovieService;

@Controller
public class MainController {
	@Autowired
	 private MovieService movieService;
	
	//영화 목록
	@RequestMapping(value = "/mainPage", method = RequestMethod.GET )
	public String index(Model model) throws Exception{
		model.addAttribute("list",movieService.movieList()); //영화 리스트
		return "index";
	}
	
	//영화 상세
	@RequestMapping(value="/movie/detail/{movieId}" ,method= RequestMethod.GET)
	public String movieDetail(@PathVariable("movieId") int movieId, Model model) throws Exception {
		model.addAttribute("movie",movieService.movieDetail(movieId));// 영화 상세
		model.addAttribute("recommend",movieService.recommendMovie(movieId)); // 추천영화 정보
		
		return "movieDetail";
	}
	
}
