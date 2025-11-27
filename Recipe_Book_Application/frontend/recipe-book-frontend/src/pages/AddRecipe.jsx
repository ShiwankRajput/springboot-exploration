// src/pages/AddRecipe.jsx
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { recipesAPI } from '../services/api';
import RecipeForm from '../components/RecipeForm';

const AddRecipe = () => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (recipeData) => {
    try {
      setLoading(true);
      setError('');
      
      await recipesAPI.createRecipe(recipeData);
      navigate('/my-recipes');
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to create recipe');
      console.error('Error creating recipe:', err);
    } finally {
      setLoading(false);
    }
  };

  const handleCancel = () => {
    navigate('/my-recipes');
  };

  return (
    <div className="container mt-4">
      <div className="row justify-content-center">
        <div className="col-12 col-lg-8">
          <div className="card shadow">
            <div className="card-body p-4">
              <div className="d-flex justify-content-between align-items-center mb-4">
                <h2 className="text-primary">Add New Recipe</h2>
                <button 
                  className="btn btn-outline-secondary"
                  onClick={handleCancel}
                >
                  Cancel
                </button>
              </div>
              
              {error && (
                <div className="alert alert-danger" role="alert">
                  {error}
                </div>
              )}

              <RecipeForm 
                onSubmit={handleSubmit}
                onCancel={handleCancel}
                loading={loading}
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddRecipe;