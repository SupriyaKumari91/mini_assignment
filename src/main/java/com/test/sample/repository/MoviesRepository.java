package com.test.sample.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.sample.model.Movies;

@Repository
public interface MoviesRepository extends CrudRepository<Movies, Integer> {
	
	
	@Query(nativeQuery = true,value = "SELECT * FROM movies m WHERE m.titles=:title and m.reviews_from_users>:ratings ORDER BY m.reviews_from_users DESC")
	public Optional<List<Movies>> descMovieRatings(@Param("title") String title,@Param("ratings") String ratings);

	
    @Query(nativeQuery=true,value="Select * from movies m where m.director=:director AND m.year BETWEEN :startYearRange AND :endYearRange ")
	public Optional<List<String>> descMovieDetails( @Param("director") String director,@Param("startYearRange") String startYearRange,@Param("endYearRange") String endYearRange);

    @Query(nativeQuery = true, value = "SELECT * FROM movies m WHERE m.budget = (SELECT max(budget) from movies where year=:year AND country=:country)")
	public Optional<List<Movies>> highestBudgetMovieDetails(@Param("year")String year,@Param("country")String country);
}
