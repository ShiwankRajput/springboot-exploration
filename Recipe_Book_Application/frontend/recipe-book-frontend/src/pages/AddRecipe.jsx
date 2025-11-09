import { useState } from "react";
import axios from "axios";

export default function AddRecipe() {
  const [title, setTitle] = useState("");
  const [ingredients, setIngredients] = useState([""]);
  const [instructions, setInstructions] = useState([""]);
  const [success, setSuccess] = useState(false);

  const handleAddIngredient = () => {
    setIngredients([...ingredients, ""]);
  };

  const handleAddInstruction = () => {
    setInstructions([...instructions, ""]);
  };

  const handleIngredientChange = (value, index) => {
    const updated = [...ingredients];
    updated[index] = value;
    setIngredients(updated);
  };

  const handleInstructionChange = (value, index) => {
    const updated = [...instructions];
    updated[index] = value;
    setInstructions(updated);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const data = {
      title,
      ingredients,
      instructions,
    };

    try {
      await axios.post(
        "http://localhost:8080/api/recipeBook/recipes",
        data
      );
      setSuccess(true);
      setTitle("");
      setIngredients([""]);
      setInstructions([""]);
    } catch (err) {
      console.error("Error saving recipe:", err);
    }
  };

  return (
    <div
      style={{
        minHeight: "100vh",
        background: "linear-gradient(135deg, #7b4397, #dc2430)",
        padding: "40px 20px",
      }}
      className="d-flex align-items-center justify-content-center"
    >
      <div
        className="container"
        style={{
          maxWidth: "900px",
        }}
      >
        <div
          className="card shadow-lg p-4"
          style={{
            borderRadius: "20px",
            background: "rgba(255,255,255,0.15)",
            backdropFilter: "blur(15px)",
            border: "1px solid rgba(255,255,255,0.3)",
            color: "white",
          }}
        >
          <h2 className="text-center fw-bold mb-4">
            Add <span style={{ color: "#ffe082" }}>New Recipe</span>
          </h2>

          {success && (
            <div className="alert alert-success text-center fw-bold">
              ✅ Recipe Added Successfully!
            </div>
          )}

          <form onSubmit={handleSubmit}>
            {/* Title */}
            <div className="mb-4">
              <label className="form-label fw-bold">Recipe Title</label>
              <input
                type="text"
                required
                className="form-control shadow-sm"
                style={{
                  background: "rgba(255,255,255,0.85)",
                  borderRadius: "10px",
                }}
                placeholder="Enter recipe title..."
                value={title}
                onChange={(e) => setTitle(e.target.value)}
              />
            </div>

            {/* Ingredients */}
            <div className="mb-4">
              <label className="form-label fw-bold">Ingredients</label>

              {ingredients.map((ing, index) => (
                <input
                  key={index}
                  type="text"
                  className="form-control mb-2 shadow-sm"
                  style={{
                    background: "rgba(255,255,255,0.85)",
                    borderRadius: "10px",
                  }}
                  placeholder={`Ingredient ${index + 1}`}
                  value={ing}
                  onChange={(e) =>
                    handleIngredientChange(e.target.value, index)
                  }
                />
              ))}

              <button
                type="button"
                className="btn btn-light btn-sm mt-2"
                style={{ borderRadius: "10px" }}
                onClick={handleAddIngredient}
              >
                ➕ Add Ingredient
              </button>
            </div>

            {/* Instructions */}
            <div className="mb-4">
              <label className="form-label fw-bold">Instructions</label>

              {instructions.map((inst, index) => (
                <input
                  key={index}
                  type="text"
                  className="form-control mb-2 shadow-sm"
                  style={{
                    background: "rgba(255,255,255,0.85)",
                    borderRadius: "10px",
                  }}
                  placeholder={`Step ${index + 1}`}
                  value={inst}
                  onChange={(e) =>
                    handleInstructionChange(e.target.value, index)
                  }
                />
              ))}

              <button
                type="button"
                className="btn btn-light btn-sm mt-2"
                style={{ borderRadius: "10px" }}
                onClick={handleAddInstruction}
              >
                ➕ Add Step
              </button>
            </div>

            {/* Submit Button */}
            <div className="text-center">
              <button
                type="submit"
                className="btn btn-warning fw-bold px-4 mt-2 shadow"
                style={{
                  borderRadius: "15px",
                  fontSize: "18px",
                }}
              >
                ✅ Save Recipe
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
