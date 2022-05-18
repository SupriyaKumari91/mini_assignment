package com.test.sample.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.sample.model.Movies;
import com.test.sample.service.MovieService;
import com.test.sample.utillities.ResourceNotFoundException;



@RestController
public class MovieController {
	private static Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;
	
	@GetMapping("/movies/sorted/desc")
	public ResponseEntity<List<Movies>> sortedAscComments(@RequestParam("English_Titles") String englishTitles
			,@RequestParam("User_Ratings") String userRatings,@RequestParam("X-Auth-Token") String authToken) throws ResourceNotFoundException{
		logger.info("get sorted movie desc ...!");
		List<Movies> movies = movieService.sortedDescComments(englishTitles,userRatings,authToken);
		return new ResponseEntity<>(movies, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/movies/details")
	public ResponseEntity<List<Movies>> getMovieDirectedByGivenDirector(@RequestParam("Director") String director
			,@RequestParam("Start-Year-Range") String startYearRange,@RequestParam("End-Year-Range") String endYearRange,
			@RequestParam("X-Auth-Token") String authToken) throws ResourceNotFoundException{
		logger.info("get movie details ...!");
		List<Movies> movies = movieService.getMoviesDetails(director,startYearRange,endYearRange,authToken);
		return new ResponseEntity<>(movies, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/movies/highest/budget")
	public ResponseEntity<List<Movies>> getHighestBudgetTitles(@RequestParam("Year") String year
			,@RequestParam("Country") String country,@RequestParam("X-Auth-Token") String authToken) throws ResourceNotFoundException{
		logger.info("get highest budget movies ...!");
		List<Movies> movies = movieService.highestBudgetMovies(year,country,authToken);
		return new ResponseEntity<>(movies, new HttpHeaders(), HttpStatus.OK);
	}

}
