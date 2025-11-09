import axios from "axios";

const BASE_URL = "http://localhost:8080/api/recipeBook/recipes";

export const fetchRecipes = () => axios.get(BASE_URL);
export const fetchRecipeById = (id) => axios.get(`${BASE_URL}/${id}`);
export const createRecipe = (recipe) => axios.post(BASE_URL, recipe);
export const updateRecipe = (id, recipe) => axios.put(`${BASE_URL}/${id}`, recipe);
export const deleteRecipe = (id) => axios.delete(`${BASE_URL}/${id}`);
