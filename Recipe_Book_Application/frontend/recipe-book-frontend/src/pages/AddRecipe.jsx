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

  const removeIngredient = (index) => {
    if (ingredients.length > 1) {
      const updated = ingredients.filter((_, i) => i !== index);
      setIngredients(updated);
    }
  };

  const removeInstruction = (index) => {
    if (instructions.length > 1) {
      const updated = instructions.filter((_, i) => i !== index);
      setInstructions(updated);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const data = {
      title,
      ingredients: ingredients.filter(ing => ing.trim() !== ""),
      instructions: instructions.filter(inst => inst.trim() !== ""),
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
      
      setTimeout(() => setSuccess(false), 3000);
    } catch (err) {
      console.error("Error saving recipe:", err);
    }
  };

  return (
    <div className="min-vh-100 bg-light py-5">
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-lg-8 col-xl-6">
            <div className="card shadow-lg border-0">
              <div className="card-header bg-white py-4 border-0">
                <div className="text-center">
                  <div className="bg-success rounded-circle d-inline-flex align-items-center justify-content-center mb-3" 
                       style={{width: '60px', height: '60px'}}>
                    <span className="text-white fs-4">➕</span>
                  </div>
                  <h2 className="text-dark fw-bold mb-0">Add New Recipe</h2>
                </div>
              </div>
              
              <div className="card-body p-4">
                {success && (
                  <div className="alert alert-success alert-dismissible fade show rounded-3 border-0 shadow-sm mb-4">
                    <div className="d-flex align-items-center">
                      <span className="fw-semibold">Recipe added successfully!</span>
                    </div>
                    <button 
                      type="button" 
                      className="btn-close" 
                      onClick={() => setSuccess(false)}
                    ></button>
                  </div>
                )}

                <form onSubmit={handleSubmit}>
                  {/* Title */}
                  <div className="mb-4">
                    <label className="form-label fw-semibold text-dark">Recipe Title</label>
                    <input
                      type="text"
                      required
                      className="form-control form-control-lg border-0 shadow-sm rounded-2"
                      style={{background: '#f8f9fa'}}
                      placeholder="Enter recipe title..."
                      value={title}
                      onChange={(e) => setTitle(e.target.value)}
                    />
                  </div>

                  {/* Ingredients */}
                  <div className="mb-4">
                    <label className="form-label fw-semibold text-dark">Ingredients</label>
                    {ingredients.map((ing, index) => (
                      <div className="d-flex mb-2" key={index}>
                        <input
                          type="text"
                          className="form-control border-0 shadow-sm rounded-2 me-2"
                          style={{background: '#f8f9fa'}}
                          placeholder={`Ingredient ${index + 1}`}
                          value={ing}
                          onChange={(e) =>
                            handleIngredientChange(e.target.value, index)
                          }
                        />
                        {ingredients.length > 1 && (
                          <button
                            type="button"
                            className="btn btn-outline-danger border-0 rounded-2"
                            style={{width: '50px'}}
                            onClick={() => removeIngredient(index)}
                          >
                            ×
                          </button>
                        )}
                      </div>
                    ))}
                    <button
                      type="button"
                      className="btn btn-outline-dark rounded-pill mt-2"
                      onClick={handleAddIngredient}
                    >
                      Add Ingredient
                    </button>
                  </div>

                  {/* Instructions */}
                  <div className="mb-4">
                    <label className="form-label fw-semibold text-dark">Instructions</label>
                    {instructions.map((inst, index) => (
                      <div className="d-flex mb-2" key={index}>
                        <input
                          type="text"
                          className="form-control border-0 shadow-sm rounded-2 me-2"
                          style={{background: '#f8f9fa'}}
                          placeholder={`Step ${index + 1}`}
                          value={inst}
                          onChange={(e) =>
                            handleInstructionChange(e.target.value, index)
                          }
                        />
                        {instructions.length > 1 && (
                          <button
                            type="button"
                            className="btn btn-outline-danger border-0 rounded-2"
                            style={{width: '50px'}}
                            onClick={() => removeInstruction(index)}
                          >
                            ×
                          </button>
                        )}
                      </div>
                    ))}
                    <button
                      type="button"
                      className="btn btn-outline-dark rounded-pill mt-2"
                      onClick={handleAddInstruction}
                    >
                      Add Step
                    </button>
                  </div>

                  {/* Submit Button */}
                  <div className="text-center mt-4 pt-3">
                    <button
                      type="submit"
                      className="btn btn-dark btn-lg px-5 rounded-pill fw-semibold"
                    >
                      Save Recipe
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}