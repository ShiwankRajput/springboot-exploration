import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

function EditRecipe() {
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
    const newList = [...recipe[type]];
    newList.splice(index, 1);
    setRecipe({ ...recipe, [type]: newList });
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
    <div className="container mt-5">
      <div className="card shadow-lg p-4 border-0" style={{ borderRadius: 20 }}>
        <h2 className="text-center mb-4">Edit Recipe</h2>
        
        <form onSubmit={handleSubmit}>
          
          {/* Title */}
          <div className="mb-3">
            <label className="form-label fw-bold">Recipe Title</label>
            <input
              type="text"
              name="title"
              className="form-control"
              value={recipe.title}
              onChange={handleChange}
              required
              placeholder="Enter recipe title"
            />
          </div>

          {/* Ingredients */}
          <div className="mb-3">
            <label className="form-label fw-bold">Ingredients</label>
            {recipe.ingredients.map((item, index) => (
              <div className="input-group mb-2" key={index}>
                <input
                  type="text"
                  className="form-control"
                  value={item}
                  onChange={(e) =>
                    handleListChange(index, "ingredients", e.target.value)
                  }
                  placeholder={`Ingredient ${index + 1}`}
                />
                <button
                  type="button"
                  className="btn btn-danger"
                  onClick={() => removeListItem(index, "ingredients")}
                  disabled={recipe.ingredients.length === 1}
                >
                  X
                </button>
              </div>
            ))}
            <button
              type="button"
              className="btn btn-secondary btn-sm"
              onClick={() => addListItem("ingredients")}
            >
              + Add Ingredient
            </button>
          </div>

          {/* Instructions */}
          <div className="mb-3">
            <label className="form-label fw-bold">Instructions</label>
            {recipe.instructions.map((item, index) => (
              <div className="input-group mb-2" key={index}>
                <input
                  type="text"
                  className="form-control"
                  value={item}
                  onChange={(e) =>
                    handleListChange(index, "instructions", e.target.value)
                  }
                  placeholder={`Instruction step ${index + 1}`}
                />
                <button
                  type="button"
                  className="btn btn-danger"
                  onClick={() => removeListItem(index, "instructions")}
                  disabled={recipe.instructions.length === 1}
                >
                  X
                </button>
              </div>
            ))}
            <button
              type="button"
              className="btn btn-secondary btn-sm"
              onClick={() => addListItem("instructions")}
            >
              + Add Instruction
            </button>
          </div>

          {/* Submit Button */}
          <div className="text-center mt-4">
            <button className="btn btn-primary px-4 py-2" type="submit">
              Update Recipe
            </button>
          </div>

        </form>
      </div>
    </div>
  );
}

export default EditRecipe;
