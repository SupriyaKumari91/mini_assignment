package com.test.sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Movies {

	@Id
	@Column
	private int id;
	@Column
	private String imdb_title_id;
	@Column
	private String title;
	@Column
	private String original_title;
	@Column
	private String year;
	@Column
	private String date_published;
	@Column
	private String genre;
	@Column
	private String duration;
	@Column
	private String country;
	@Column
	private String language;
	@Column
	private String director;
	@Column
	private String writer;
	@Column
	private String production_company;
	@Column
	private String actors;
	@Column
	private String description;
	@Column
	private String avg_vote;
	@Column
	private String votes;
	@Column
	private String budget;
	@Column
	private String usa_gross_income;
	@Column
	private String worlwide_gross_income;
	@Column
	private String metascore;
	@Column
	private String reviews_from_users;
	@Column
	private String reviews_from_critics;

}