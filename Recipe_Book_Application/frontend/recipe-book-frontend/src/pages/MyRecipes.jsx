// src/pages/MyRecipes.jsx
import { useState, useEffect } from 'react';
import { recipesAPI } from '../services/api';
import RecipeCard from '../components/RecipeCard';
import { useAuth } from '../context/AuthContext';

const MyRecipes = () => {
  const [recipes, setRecipes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const { user } = useAuth();

  useEffect(() => {
    fetchMyRecipes();
  }, []);

  const fetchMyRecipes = async () => {
    try {
      setLoading(true);
      const response = await recipesAPI.getMyRecipes();
      setRecipes(response.data);
    } catch (err) {
      setError('Failed to fetch your recipes');
      console.error('Error fetching recipes:', err);
    } finally {
      setLoading(false);
    }
  };

  const handleEdit = (recipe) => {
    // Navigate to edit page or show modal
    alert(`Edit recipe: ${recipe.title}`);
  };

  const handleDelete = async (recipeId) => {
    if (window.confirm('Are you sure you want to delete this recipe?')) {
      try {
        await recipesAPI.deleteRecipe(recipeId);
        setRecipes(recipes.filter(recipe => recipe.id !== recipeId));
      } catch (err) {
        setError('Failed to delete recipe');
        console.error('Error deleting recipe:', err);
      }
    }
  };

  if (loading) {
    return (
      <div className="container mt-4">
        <div className="d-flex justify-content-center">
          <div className="spinner-border text-primary" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="container mt-4">
      <div className="row">
        <div className="col-12">
          <div className="d-flex justify-content-between align-items-center mb-4">
            <h1 className="text-primary">My Recipes</h1>
            <span className="badge bg-primary fs-6">{recipes.length} recipes</span>
          </div>
          
          {error && (
            <div className="alert alert-danger" role="alert">
              {error}
            </div>
          )}

          {recipes.length === 0 ? (
            <div className="text-center py-5">
              <h4 className="text-muted">You haven't created any recipes yet</h4>
              <p className="text-muted">Start by adding your first recipe!</p>
              <a href="/add-recipe" className="btn btn-primary mt-3">Add Your First Recipe</a>
            </div>
          ) : (
            <div className="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
              {recipes.map(recipe => (
                <div key={recipe.id} className="col">
                  <RecipeCard 
                    recipe={recipe}
                    onEdit={handleEdit}
                    onDelete={handleDelete}
                  />
                </div>
              ))}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default MyRecipes;