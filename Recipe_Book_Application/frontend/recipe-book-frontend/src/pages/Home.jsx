import { useEffect, useState } from "react";
import axios from "axios";
import RecipeCard from "../components/RecipeCard";
import SearchBar from "../components/SearchBar";

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
    <div className="min-vh-100 bg-light">
      <div className="container py-5">
        {/* Header */}
        <div className="text-center mb-5">
          <div className="bg-dark text-white rounded-circle d-inline-flex align-items-center justify-content-center mb-4" 
               style={{width: '80px', height: '80px'}}>
            <span className="fs-2">🍳</span>
          </div>
          <h1 className="display-5 fw-bold text-dark mb-3">
            Discover Recipes
          </h1>
          <p className="text-muted fs-5">
            Explore and manage your culinary creations
          </p>
        </div>

        {/* Search Bar */}
        <SearchBar searchTerm={searchTerm} setSearchTerm={handleSearch} />

        {/* Loading State */}
        {loading && (
          <div className="text-center py-5">
            <div className="spinner-border text-dark" role="status">
              <span className="visually-hidden">Loading...</span>
            </div>
            <p className="mt-3 text-muted">Loading recipes...</p>
          </div>
        )}

        {/* Recipes Grid */}
        {!loading && (
          <>
            {filteredRecipes.length === 0 ? (
              <div className="text-center py-5">
                <div className="card border-0 bg-white shadow-sm mx-auto" style={{maxWidth: '400px'}}>
                  <div className="card-body py-5">
                    <div className="text-muted mb-3 fs-1">📝</div>
                    <h5 className="text-dark mb-3">No recipes found</h5>
                    <p className="text-muted mb-0">
                      {searchTerm ? 'Try different search terms' : 'Get started by adding your first recipe'}
                    </p>
                  </div>
                </div>
              </div>
            ) : (
              <div className="row g-4">
                {filteredRecipes.map((recipe) => (
                  <div className="col-lg-6 col-xl-4" key={recipe.id}>
                    <RecipeCard recipe={recipe} />
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