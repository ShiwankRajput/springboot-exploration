// src/components/RecipeForm.jsx
import { useState } from 'react';

const RecipeForm = ({ recipe, onSubmit, onCancel }) => {
  const [formData, setFormData] = useState({
    title: recipe?.title || '',
    ingredients: recipe?.ingredients || [''],
    instructions: recipe?.instructions || ['']
  });

  const [errors, setErrors] = useState({});

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleArrayChange = (field, index, value) => {
    setFormData(prev => ({
      ...prev,
      [field]: prev[field].map((item, i) => i === index ? value : item)
    }));
  };

  const addArrayField = (field) => {
    setFormData(prev => ({
      ...prev,
      [field]: [...prev[field], '']
    }));
  };

  const removeArrayField = (field, index) => {
    if (formData[field].length > 1) {
      setFormData(prev => ({
        ...prev,
        [field]: prev[field].filter((_, i) => i !== index)
      }));
    }
  };

  const validateForm = () => {
    const newErrors = {};
    
    if (!formData.title.trim()) {
      newErrors.title = 'Title is required';
    }
    
    const validIngredients = formData.ingredients.filter(ing => ing.trim());
    if (validIngredients.length === 0) {
      newErrors.ingredients = 'At least one ingredient is required';
    }
    
    const validInstructions = formData.instructions.filter(inst => inst.trim());
    if (validInstructions.length === 0) {
      newErrors.instructions = 'At least one instruction is required';
    }
    
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    
    if (validateForm()) {
      const submitData = {
        ...formData,
        ingredients: formData.ingredients.filter(ing => ing.trim()),
        instructions: formData.instructions.filter(inst => inst.trim())
      };
      
      onSubmit(submitData);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="mb-3">
        <label htmlFor="title" className="form-label">Recipe Title</label>
        <input
          type="text"
          className={`form-control ${errors.title ? 'is-invalid' : ''}`}
          id="title"
          name="title"
          value={formData.title}
          onChange={handleInputChange}
          placeholder="Enter recipe title"
        />
        {errors.title && <div className="invalid-feedback">{errors.title}</div>}
      </div>

      <div className="mb-3">
        <label className="form-label">Ingredients</label>
        {errors.ingredients && <div className="text-danger small mb-2">{errors.ingredients}</div>}
        
        {formData.ingredients.map((ingredient, index) => (
          <div key={index} className="input-group mb-2">
            <input
              type="text"
              className="form-control"
              value={ingredient}
              onChange={(e) => handleArrayChange('ingredients', index, e.target.value)}
              placeholder={`Ingredient ${index + 1}`}
            />
            <button
              type="button"
              className="btn btn-outline-danger"
              onClick={() => removeArrayField('ingredients', index)}
              disabled={formData.ingredients.length === 1}
            >
              Remove
            </button>
          </div>
        ))}
        
        <button
          type="button"
          className="btn btn-outline-primary btn-sm"
          onClick={() => addArrayField('ingredients')}
        >
          + Add Ingredient
        </button>
      </div>

      <div className="mb-3">
        <label className="form-label">Instructions</label>
        {errors.instructions && <div className="text-danger small mb-2">{errors.instructions}</div>}
        
        {formData.instructions.map((instruction, index) => (
          <div key={index} className="mb-2">
            <div className="input-group">
              <span className="input-group-text">Step {index + 1}</span>
              <textarea
                className="form-control"
                rows="2"
                value={instruction}
                onChange={(e) => handleArrayChange('instructions', index, e.target.value)}
                placeholder={`Instruction step ${index + 1}`}
              />
              <button
                type="button"
                className="btn btn-outline-danger"
                onClick={() => removeArrayField('instructions', index)}
                disabled={formData.instructions.length === 1}
              >
                Remove
              </button>
            </div>
          </div>
        ))}
        
        <button
          type="button"
          className="btn btn-outline-primary btn-sm"
          onClick={() => addArrayField('instructions')}
        >
          + Add Instruction
        </button>
      </div>

      <div className="d-flex gap-2">
        <button type="submit" className="btn btn-primary">
          {recipe ? 'Update Recipe' : 'Create Recipe'}
        </button>
        <button type="button" className="btn btn-secondary" onClick={onCancel}>
          Cancel
        </button>
      </div>
    </form>
  );
};

export default RecipeForm;