import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

export default function EditRecipe() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [recipe, setRecipe] = useState({
    title: "",
    ingredients: [""],
    instructions: [""],
  });

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/recipeBook/recipes/${id}`)
      .then((res) => {
        setRecipe(res.data);
      })
      .catch((err) => console.log("Error loading recipe:", err));
  }, [id]);

  const handleChange = (e) => {
    setRecipe({ ...recipe, [e.target.name]: e.target.value });
  };

  const handleListChange = (index, type, value) => {
    const newList = [...recipe[type]];
    newList[index] = value;
    setRecipe({ ...recipe, [type]: newList });
  };

  const addListItem = (type) => {
    setRecipe({ ...recipe, [type]: [...recipe[type], ""] });
  };

  const removeListItem = (index, type) => {
    if (recipe[type].length > 1) {
      const newList = recipe[type].filter((_, i) => i !== index);
      setRecipe({ ...recipe, [type]: newList });
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .put(`http://localhost:8080/api/recipeBook/recipes/${id}`, recipe)
      .then(() => {
        alert("Recipe updated successfully!");
        navigate("/");
      })
      .catch((err) => console.log("Update Error:", err));
  };

  return (
    <div className="min-vh-100 bg-light py-5">
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-lg-8 col-xl-6">
            <div className="card shadow-lg border-0">
              <div className="card-header bg-white py-4 border-0">
                <div className="text-center">
                  <div className="bg-warning rounded-circle d-inline-flex align-items-center justify-content-center mb-3" 
                       style={{width: '60px', height: '60px'}}>
                    <span className="text-dark fs-4">✏️</span>
                  </div>
                  <h2 className="text-dark fw-bold mb-0">Edit Recipe</h2>
                </div>
              </div>
              
              <div className="card-body p-4">
                <form onSubmit={handleSubmit}>
                  
                  {/* Title */}
                  <div className="mb-4">
                    <label className="form-label fw-semibold text-dark">Recipe Title</label>
                    <input
                      type="text"
                      name="title"
                      className="form-control form-control-lg border-0 shadow-sm rounded-2"
                      style={{background: '#f8f9fa'}}
                      value={recipe.title}
                      onChange={handleChange}
                      required
                      placeholder="Enter recipe title"
                    />
                  </div>

                  {/* Ingredients */}
                  <div className="mb-4">
                    <label className="form-label fw-semibold text-dark">Ingredients</label>
                    {recipe.ingredients.map((item, index) => (
                      <div className="d-flex mb-2" key={index}>
                        <input
                          type="text"
                          className="form-control border-0 shadow-sm rounded-2 me-2"
                          style={{background: '#f8f9fa'}}
                          value={item}
                          onChange={(e) =>
                            handleListChange(index, "ingredients", e.target.value)
                          }
                          placeholder={`Ingredient ${index + 1}`}
                        />
                        <button
                          type="button"
                          className="btn btn-outline-danger border-0 rounded-2"
                          style={{width: '50px'}}
                          onClick={() => removeListItem(index, "ingredients")}
                          disabled={recipe.ingredients.length === 1}
                        >
                          ×
                        </button>
                      </div>
                    ))}
                    <button
                      type="button"
                      className="btn btn-outline-dark rounded-pill mt-2"
                      onClick={() => addListItem("ingredients")}
                    >
                      Add Ingredient
                    </button>
                  </div>

                  {/* Instructions */}
                  <div className="mb-4">
                    <label className="form-label fw-semibold text-dark">Instructions</label>
                    {recipe.instructions.map((item, index) => (
                      <div className="d-flex mb-2" key={index}>
                        <input
                          type="text"
                          className="form-control border-0 shadow-sm rounded-2 me-2"
                          style={{background: '#f8f9fa'}}
                          value={item}
                          onChange={(e) =>
                            handleListChange(index, "instructions", e.target.value)
                          }
                          placeholder={`Instruction step ${index + 1}`}
                        />
                        <button
                          type="button"
                          className="btn btn-outline-danger border-0 rounded-2"
                          style={{width: '50px'}}
                          onClick={() => removeListItem(index, "instructions")}
                          disabled={recipe.instructions.length === 1}
                        >
                          ×
                        </button>
                      </div>
                    ))}
                    <button
                      type="button"
                      className="btn btn-outline-dark rounded-pill mt-2"
                      onClick={() => addListItem("instructions")}
                    >
                      Add Instruction
                    </button>
                  </div>

                  {/* Submit Button */}
                  <div className="text-center mt-4 pt-3">
                    <button className="btn btn-warning btn-lg px-5 rounded-pill fw-semibold text-dark" type="submit">
                      Update Recipe
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