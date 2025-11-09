package com.RecipeBook.RecipeBookApplication.Controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.RecipeBook.RecipeBookApplication.entity.Recipe;
import com.RecipeBook.RecipeBookApplication.exception.RecipeNotFoundException;
import com.RecipeBook.RecipeBookApplication.service.RecipeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/recipeBook")
@CrossOrigin(origins = "http://localhost:5173")
public class RecipeController {
	
	private RecipeService service;
	
	public RecipeController(RecipeService service) {
		this.service = service;
	}

	@GetMapping("/recipes")
	public List<Recipe> getAllRecipes(){
		return service.getAllRecipes();
	}
	
	@PostMapping("/recipes")
	public ResponseEntity<Recipe> createRecipe(@Valid @RequestBody Recipe recipe) {
		service.addNewRecipe(recipe);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(recipe.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/recipes/{id}")
	public Recipe getRecipeById(@PathVariable long id) {
		Optional<Recipe> recipe = service.getSpecificRecipe(id);
		
		if(recipe.isPresent()) {
			return recipe.get();
		}
		else {
			throw new RecipeNotFoundException("Recipe not found for id -> " + id);
		}

	}
	
	@PutMapping("/recipes/{id}")
	public void updateRecipeById(@PathVariable long id, @Valid @RequestBody Recipe recipe) {
		service.updateSpecificRecipe(id, recipe);
	}
	
	@DeleteMapping("/recipes/{id}")
	public void deleteRecipeById(@PathVariable long id) {
		service.deleteSpecificRecipe(id);
	}
	
	
}
