import { Link } from "react-router-dom";

export default function RecipeCard({ recipe }) {
  return (
    <div className="card h-100 shadow-sm border-0 transition-all hover-shadow" style={{minHeight: '500px'}}>
      <div className="card-body d-flex flex-column">
        {/* Recipe Title */}
        <h5 className="card-title text-dark fw-bold mb-3 border-bottom pb-3 text-center">
          {recipe.title}
        </h5>
        
        {/* Ingredients Section */}
        <div className="mb-4">
          <h6 className="text-success fw-semibold mb-3 d-flex align-items-center">
            <span className="bg-success text-white rounded-circle d-inline-flex align-items-center justify-content-center me-2" 
                  style={{width: '24px', height: '24px', fontSize: '12px'}}>
              I
            </span>
            Ingredients
          </h6>
          <ul className="list-unstyled mb-0">
            {recipe.ingredients.map((ing, i) => (
              <li key={i} className="text-secondary py-2 border-bottom d-flex align-items-start">
                <span className="text-success me-2">•</span>
                <span>{ing}</span>
              </li>
            ))}
          </ul>
        </div>

        {/* Instructions Section */}
        <div className="flex-grow-1">
          <h6 className="text-success fw-semibold mb-3 d-flex align-items-center">
            <span className="bg-success text-white rounded-circle d-inline-flex align-items-center justify-content-center me-2" 
                  style={{width: '24px', height: '24px', fontSize: '12px'}}>
              S
            </span>
            Instructions
          </h6>
          <ol className="ps-0 mb-0">
            {recipe.instructions.map((inst, i) => (
              <li key={i} className="text-secondary mb-3 d-flex">
                <span className="text-success fw-bold me-3">{i + 1}.</span>
                <span>{inst}</span>
              </li>
            ))}
          </ol>
        </div>

        {/* Edit Button */}
        <div className="mt-4 pt-3 border-top">
          <Link 
            to={`/edit/${recipe.id}`} 
            className="btn btn-outline-dark w-100 rounded-pill fw-semibold py-2"
          >
            Edit Recipe
          </Link>
        </div>
      </div>
    </div>
  );
}