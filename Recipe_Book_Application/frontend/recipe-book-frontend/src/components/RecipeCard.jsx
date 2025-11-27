// src/components/RecipeCard.jsx
import { useAuth } from '../context/AuthContext';

const RecipeCard = ({ recipe, onEdit, onDelete }) => {
  const { user } = useAuth();
  const isOwner = user && user.username === recipe.authorUsername;

  return (
    <div className="card h-100 shadow-sm">
      <div className="card-body">
        <h5 className="card-title text-primary">{recipe.title}</h5>
        <p className="card-text">
          <small className="text-muted">
            By: {recipe.authorUsername}
          </small>
        </p>
        
        <h6 className="mt-3">Ingredients:</h6>
        <ul className="list-unstyled">
          {recipe.ingredients.slice(0, 3).map((ingredient, index) => (
            <li key={index} className="text-muted">
              • {ingredient}
            </li>
          ))}
          {recipe.ingredients.length > 3 && (
            <li className="text-muted">• ... and {recipe.ingredients.length - 3} more</li>
          )}
        </ul>

        <h6>Instructions:</h6>
        <p className="card-text">
          {recipe.instructions[0].substring(0, 100)}...
        </p>
      </div>
      
      {isOwner && (
        <div className="card-footer bg-transparent">
          <div className="btn-group w-100">
            <button 
              className="btn btn-outline-primary btn-sm"
              onClick={() => onEdit(recipe)}
            >
              Edit
            </button>
            <button 
              className="btn btn-outline-danger btn-sm"
              onClick={() => onDelete(recipe.id)}
            >
              Delete
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default RecipeCard;