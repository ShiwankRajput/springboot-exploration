package com.RecipeBook.RecipeBookApplication.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.RecipeBook.RecipeBookApplication.dto.RecipeResponse;
import com.RecipeBook.RecipeBookApplication.entity.Recipe;
import com.RecipeBook.RecipeBookApplication.entity.User;
import com.RecipeBook.RecipeBookApplication.repository.RecipeRepository;

@Service
public class RecipeService {

    private RecipeRepository repository;
    private UserService userService;
    
    public RecipeService(RecipeRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }
    
    public List<RecipeResponse> getAllRecipes() {
        return repository.findAll().stream()
                .map(this::convertToRecipeResponse)
                .collect(Collectors.toList());
    }
    
    public List<RecipeResponse> getUserRecipes(String username) {
        User user = userService.getUserByUsername(username);
        return repository.findByUserId(user.getId()).stream()
                .map(this::convertToRecipeResponse)
                .collect(Collectors.toList());
    }
    
    public void addNewRecipe(Recipe recipe, String username) {
        User user = userService.getUserByUsername(username);
        recipe.setUser(user);
        repository.save(recipe);
    }
    
    public Optional<RecipeResponse> getSpecificRecipe(long id) {
        return repository.findById(id)
                .map(this::convertToRecipeResponse);
    }
    
    public void updateSpecificRecipe(long id, Recipe recipe, String username) {
        Recipe existingRecipe = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
        
        // Check if the recipe belongs to the user
        if (!existingRecipe.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You can only update your own recipes");
        }
        
        existingRecipe.setTitle(recipe.getTitle());
        existingRecipe.setIngredients(recipe.getIngredients());
        existingRecipe.setInstructions(recipe.getInstructions());
        repository.save(existingRecipe);
    }
    
    public void deleteSpecificRecipe(long id, String username) {
        Recipe recipe = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
        
        // Check if the recipe belongs to the user
        if (!recipe.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You can only delete your own recipes");
        }
        
        repository.deleteById(id);
    }
    
    private RecipeResponse convertToRecipeResponse(Recipe recipe) {
        return new RecipeResponse(
            recipe.getId(),
            recipe.getTitle(),
            recipe.getIngredients(),
            recipe.getInstructions(),
            recipe.getUser().getUsername()
        );
    }
}