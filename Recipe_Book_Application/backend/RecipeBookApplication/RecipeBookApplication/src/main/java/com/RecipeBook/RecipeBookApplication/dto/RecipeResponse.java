package com.RecipeBook.RecipeBookApplication.dto;

import java.util.List;

public class RecipeResponse {
    private long id;
    private String title;
    private List<String> ingredients;
    private List<String> instructions;
    private String authorUsername;
    
    public RecipeResponse() {}
    
    public RecipeResponse(long id, String title, List<String> ingredients, 
                         List<String> instructions, String authorUsername) {
        this.id = id;
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.authorUsername = authorUsername;
    }
    
    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public List<String> getIngredients() { return ingredients; }
    public void setIngredients(List<String> ingredients) { this.ingredients = ingredients; }
    
    public List<String> getInstructions() { return instructions; }
    public void setInstructions(List<String> instructions) { this.instructions = instructions; }
    
    public String getAuthorUsername() { return authorUsername; }
    public void setAuthorUsername(String authorUsername) { this.authorUsername = authorUsername; }
}