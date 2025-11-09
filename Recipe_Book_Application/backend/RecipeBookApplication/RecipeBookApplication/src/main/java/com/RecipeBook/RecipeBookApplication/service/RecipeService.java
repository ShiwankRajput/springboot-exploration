package com.RecipeBook.RecipeBookApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.RecipeBook.RecipeBookApplication.Repository.RecipeRepository;
import com.RecipeBook.RecipeBookApplication.entity.Recipe;

@Service
public class RecipeService {

	private RecipeRepository repository;
	
	public RecipeService(RecipeRepository repository) {
		this.repository = repository;
	}
	
	public List<Recipe> getAllRecipes(){
		return repository.findAll();
	}
	
	public void addNewRecipe(Recipe recipe) {
		repository.save(recipe);
	}
	
	public Optional<Recipe> getSpecificRecipe(long id) {
		return repository.findById(id);
	}
	
	public void updateSpecificRecipe(long id, Recipe recipe) {
		Recipe existingRecipe = repository.findById(id).get();
		existingRecipe.setTitle(recipe.getTitle());
		existingRecipe.setIngredients(recipe.getIngredients());
		existingRecipe.setInstructions(recipe.getInstructions());
		repository.save(existingRecipe);
	}
	
	public void deleteSpecificRecipe(long id) {
		repository.deleteById(id);
	}
	
}
