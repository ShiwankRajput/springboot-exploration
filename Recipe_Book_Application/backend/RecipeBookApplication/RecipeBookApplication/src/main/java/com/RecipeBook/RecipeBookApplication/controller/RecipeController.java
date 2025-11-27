package com.RecipeBook.RecipeBookApplication.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.RecipeBook.RecipeBookApplication.dto.RecipeResponse;
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
    public List<RecipeResponse> getAllRecipes(){
        return service.getAllRecipes();
    }
    
    @GetMapping("/my-recipes")
    public List<RecipeResponse> getUserRecipes(Authentication authentication){
        String username = authentication.getName();
        return service.getUserRecipes(username);
    }
    
    @PostMapping("/recipes")
    public ResponseEntity<Recipe> createRecipe(@Valid @RequestBody Recipe recipe, Authentication authentication) {
        String username = authentication.getName();
        service.addNewRecipe(recipe, username);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(recipe.getId())
                .toUri();
        
        return ResponseEntity.created(location).build();
    }
    
    @GetMapping("/recipes/{id}")
    public RecipeResponse getRecipeById(@PathVariable long id) {
        Optional<RecipeResponse> recipe = service.getSpecificRecipe(id);
        
        if(recipe.isPresent()) {
            return recipe.get();
        }
        else {
            throw new RecipeNotFoundException("Recipe not found for id -> " + id);
        }
    }
    
    @PutMapping("/recipes/{id}")
    public void updateRecipeById(@PathVariable long id, @Valid @RequestBody Recipe recipe, Authentication authentication) {
        String username = authentication.getName();
        service.updateSpecificRecipe(id, recipe, username);
    }
    
    @DeleteMapping("/recipes/{id}")
    public void deleteRecipeById(@PathVariable long id, Authentication authentication) {
        String username = authentication.getName();
        service.deleteSpecificRecipe(id, username);
    }
}