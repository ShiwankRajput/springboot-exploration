package com.RecipeBook.RecipeBookApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RecipeBook.RecipeBookApplication.entity.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{

}
