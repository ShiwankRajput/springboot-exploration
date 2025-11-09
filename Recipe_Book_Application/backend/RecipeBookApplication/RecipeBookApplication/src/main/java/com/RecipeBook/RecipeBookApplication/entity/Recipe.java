package com.RecipeBook.RecipeBookApplication.entity;

import java.util.List;

import com.RecipeBook.RecipeBookApplication.converter.StringListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Title must not be blank")
	private String title;
	
	@NotEmpty(message = "Ingredients must not be blank")
	@Convert(converter = StringListConverter.class)
	@Column(columnDefinition = "LONGTEXT") //for adding ingredients even its length is big.
	private List<String> ingredients;
	
	@NotEmpty(message = "Instructions must not be blank")
	@Convert(converter = StringListConverter.class)
	@Column(columnDefinition = "LONGTEXT")
	private List<String> instructions;
	
	public Recipe() {
		
	}

	public Recipe(long id, String title, List<String> ingredients, List<String> instructions) {
		super();
		this.id = id;
		this.title = title;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public List<String> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}
	
	
}
