import { Link } from "react-router-dom";

export default function RecipeCard({ recipe }) {
  return (
    <div className="card shadow-sm p-3">
      <h4>{recipe.title}</h4>

      <h6>Ingredients:</h6>
      <ul>
        {recipe.ingredients.map((ing, i) => (
          <li key={i}>{ing}</li>
        ))}
      </ul>

      <h6>Instructions:</h6>
      <ul>
        {recipe.instructions.map((inst, i) => (
          <li key={i}>{inst}</li>
        ))}
      </ul>

      <Link to={`/edit/${recipe.id}`} className="btn btn-primary mt-3">
        Edit
      </Link>
    </div>
  );
}
