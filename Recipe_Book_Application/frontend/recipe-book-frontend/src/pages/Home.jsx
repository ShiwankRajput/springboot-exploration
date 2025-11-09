import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

export default function Home() {
  const [recipes, setRecipes] = useState([]);
  const [filteredRecipes, setFilteredRecipes] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchAllRecipes();
  }, []);

  const fetchAllRecipes = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/api/recipeBook/recipes"
      );
      setRecipes(response.data);
      setFilteredRecipes(response.data);
      setLoading(false);
    } catch (error) {
      console.error("Error fetching recipes: ", error);
      setLoading(false);
    }
  };

  const handleSearch = (value) => {
    setSearchTerm(value);
    if (!value.trim()) {
      setFilteredRecipes(recipes);
      return;
    }

    const filtered = recipes.filter((recipe) =>
      recipe.title.toLowerCase().includes(value.toLowerCase())
    );
    setFilteredRecipes(filtered);
  };

  return (
    <div
      style={{
        minHeight: "100vh",
        background: "linear-gradient(135deg, #1e3c72 0%, #2a5298 100%)",
        padding: "40px 20px",
      }}
    >
      <div className="container">

        {/* Heading */}
        <h1 className="text-center text-white fw-bold mb-4">
          Explore <span style={{ color: "#ffe082" }}>Recipes</span>
        </h1>

        {/* Search Bar */}
        <div className="d-flex justify-content-center mb-4">
          <input
            type="text"
            placeholder="Search recipes by title..."
            className="form-control shadow-sm"
            style={{
              maxWidth: "500px",
              borderRadius: "12px",
              padding: "12px",
            }}
            value={searchTerm}
            onChange={(e) => handleSearch(e.target.value)}
          />
        </div>

        {/* Loading */}
        {loading ? (
          <div className="text-center mt-5">
            <div className="spinner-border text-light" role="status"></div>
            <p className="mt-2 text-white fw-semibold">Loading recipes...</p>
          </div>
        ) : (
          <>
            {/* No Recipes */}
            {filteredRecipes.length === 0 ? (
              <p className="text-center text-light fs-5 fw-bold">
                ⚠️ No recipes found.
              </p>
            ) : (
              <div className="row">
                {filteredRecipes.map((recipe) => (
                  <div className="col-md-4 mb-4" key={recipe.id}>
                    <div
                      className="card shadow-lg border-0 p-3"
                      style={{
                        borderRadius: "20px",
                        background: "rgba(255,255,255,0.15)",
                        backdropFilter: "blur(15px)",
                        color: "white",
                        minHeight: "350px",
                      }}
                    >
                      <h4 className="fw-bold">{recipe.title}</h4>

                      <h6 className="mt-3" style={{ color: "#ffe082" }}>
                        Ingredients:
                      </h6>
                      <ul style={{ paddingLeft: "20px" }}>
                        {recipe.ingredients.map((ing, index) => (
                          <li key={index}>{ing}</li>
                        ))}
                      </ul>

                      <h6 className="mt-3" style={{ color: "#ffe082" }}>
                        Instructions:
                      </h6>
                      <ul style={{ paddingLeft: "20px" }}>
                        {recipe.instructions.map((inst, index) => (
                          <li key={index}>{inst}</li>
                        ))}
                      </ul>

                      <div className="text-center mt-3">
                        <Link
                          to={`/edit/${recipe.id}`}
                          className="btn btn-warning fw-bold px-4"
                          style={{ borderRadius: "12px" }}
                        >
                          Edit
                        </Link>
                      </div>
                    </div>
                  </div>
                ))}
              </div>
            )}
          </>
        )}
      </div>
    </div>
  );
}
