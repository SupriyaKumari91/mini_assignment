package com.test.sample.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.sample.model.Movies;
import com.test.sample.repository.MoviesRepository;


@Service
public class MovieService {
	private static Logger logger = LoggerFactory.getLogger(MovieService.class);
	
	@Autowired
	private MoviesRepository moviesRepository;
	
	
	public List<Movies> sortedDescComments(String englishTitles, String userRatings, String authToken)  {
		logger.info("sortedComments {}", this.getClass().getName());
		if(authToken!=null) {
		return moviesRepository.descMovieRatings(englishTitles,userRatings);
		}else {
			logger.error("getAllCommentsOfPost {} Record not found", this.getClass().getName());
			return null;
		}
		
	}


	public List<Movies> getMoviesDetails(String director, String startYearRange, String endYearRange,
			String authToken) {
		logger.info("movieDetails {}", this.getClass().getName());
		if(authToken!=null) {
			return moviesRepository.descMovieDetails(director,startYearRange,endYearRange);
			}else {
				logger.error("movieDetails {} Record not found", this.getClass().getName());
				return null;
			}
	}


	public List<Movies> highestBudgetMovies(String year, String country, String authToken) {
		logger.info("highestBudgetMovies {}", this.getClass().getName());
		if(authToken!=null) {
			return moviesRepository.highestBudgetMovieDetails(year,country);
			}else {
				logger.error("highestBudgetMovieDetails {} Record not found", this.getClass().getName());
				return null;
			}
	}

}
