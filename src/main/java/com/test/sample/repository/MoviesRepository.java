package com.test.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.sample.model.Movies;

@Repository
public interface MoviesRepository extends CrudRepository<Movies, Integer> {
	
	@Query(nativeQuery = true,value = "SELECT * FROM movies m WHERE m.titles=:title and m.reviews_from_users=:ratings ORDER BY movies desc")
	public List<Movies> descMovieRatings(@Param("title") String title,@Param("ratings") String ratings);
	
	
    @Query(nativeQuery=true,value="Select * from movies m where m.director=:director and m.year=:startYearRange and m.year=:endYearRange ")
	public List<Movies> descMovieDetails( @Param("director") String director,@Param("startYearRange") String startYearRange,@Param("endYearRange") String endYearRange);

    @Query(nativeQuery = true,value="Select max(budget) from movies as m where m.year=:year and m.country=:country")
	public List<Movies> highestBudgetMovieDetails(@Param("year")String year,@Param("country")String country);
}
