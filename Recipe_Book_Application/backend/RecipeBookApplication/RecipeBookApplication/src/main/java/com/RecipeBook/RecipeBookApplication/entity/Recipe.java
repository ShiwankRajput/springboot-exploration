package com.RecipeBook.RecipeBookApplication.entity;

import java.util.List;

import com.RecipeBook.RecipeBookApplication.converter.StringListConverter;

import jakarta.persistence.*;
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
    @Column(columnDefinition = "LONGTEXT")
    private List<String> ingredients;
    
    @NotEmpty(message = "Instructions must not be blank")
    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "LONGTEXT")
    private List<String> instructions;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    public Recipe() {}
    
    public Recipe(long id, String title, List<String> ingredients, List<String> instructions, User user) {
        super();
        this.id = id;
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.user = user;
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
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}