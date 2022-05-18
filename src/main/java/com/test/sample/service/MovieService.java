package com.test.sample.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.test.sample.model.Movies;
import com.test.sample.repository.MoviesRepository;
import com.test.sample.utillities.ResourceNotFoundException;


@Service
public class MovieService {
	private static Logger logger = LoggerFactory.getLogger(MovieService.class);
	
	@Autowired
	private MoviesRepository moviesRepository;
	
	
	public List<Movies> sortedDescComments(String englishTitles, String userRatings, String authToken)  {
		logger.info("sortedComments {}", this.getClass().getName());
		Optional<List<Movies>> movieList = moviesRepository.descMovieRatings(englishTitles,userRatings);
		if(authToken==null && !movieList.isPresent()) {
			logger.error("No Data and auth token found");
		return null;
		}else {
			return movieList.get();
		}
		
	}


	public List<String> getMoviesDetails(String director, String startYearRange, String endYearRange,
			String authToken) {
		logger.info("movieDetails {}", this.getClass().getName());
		if(authToken!=null) {
			if(Objects.isNull(director)) {
				logger.error("Please enter a valid input..");
			}
			Optional<List<String>> titleList = moviesRepository.descMovieDetails(director, startYearRange, endYearRange);
	        if(!titleList.isPresent()) {
	            logger.error("No Data Found");
	        }
	        return titleList.get();
			}else {
				logger.error("movieDetails {} Record not found", this.getClass().getName());
				return null;
			}
	}


	public List<Movies> highestBudgetMovies(String year, String country, String authToken) {
		logger.info("highestBudgetMovies {}", this.getClass().getName());
		if(authToken!=null) {
			if(Objects.isNull(country)) {
				logger.error("Please enter a valid input..");
			}
			Optional<List<Movies>> movieList = moviesRepository.highestBudgetMovieDetails(year, country);
			if(!movieList.isPresent()) {
		        logger.error("No Data Found");
			}
			return movieList.get();

			}else {
				logger.error("highestBudgetMovieDetails {} Record not found", this.getClass().getName());
				return null;
			}
	}
	
	public void loadCsvFileDataInDB() {
		List<Movies> movieList = new ArrayList<>();
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("/files/movies.csv")))) {
			String line = bufferedReader.readLine();
			while((line = bufferedReader.readLine()) != null) {
				Movies movie = parseCsvFile(line);
				movieList.add(movie);
			}
			bufferedReader.close();
			moviesRepository.saveAll(movieList);
		} catch(IOException ex) {
			logger.error("Unable to process Csv file");
			throw new ResourceNotFoundException("CSV file");
		}
	}
	
	
	private static Movies parseCsvFile(String line) {
		
		Pattern pattern = Pattern.compile(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))");
        // Splitting input with the pattern
        String[] fields = pattern.split(line);
        for (int i = 0; i < fields.length; i++) {
            // Removing the remaining double quotes
            fields[i] = fields[i].replace("\"", "");
        }
		Movies movie = extractCsvDataInModel(fields);
		return movie;
	}
	
	private static Movies extractCsvDataInModel(String[] fields) {
		Movies movie = new Movies();
		movie.setImdb_title_id(fields[0]);
		movie.setTitle(fields[1]);
		movie.setOriginal_title(fields[2]);
		movie.setYear(fields[3]);
		movie.setDate_published(fields[4]);
		movie.setGenre(fields[5]);
		movie.setDuration(fields[6]);
		movie.setCountry(fields[7]);
		movie.setLanguage(fields[8]);
		movie.setDirector(fields[9]);
		movie.setWriter(fields[10]);
		movie.setProduction_company(fields[11]);
		movie.setActors(fields[12]);
		movie.setDescription(fields[13]);
		movie.setAvg_vote(fields[14]);
		movie.setVotes(fields[15]);
		movie.setReviews_from_users(fields[16]);
		movie.setReviews_from_critics(fields[17]);
		movie.setBudget(fields.length > 17 ? fields[16] : null);
		movie.setUsa_gross_income(fields.length > 18 ? fields[17] : null);
		movie.setWorlwide_gross_income(fields.length > 19 ?fields[18] : null);
		return movie;
	}

}
