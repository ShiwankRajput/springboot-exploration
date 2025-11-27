// src/services/api.js
import axios from 'axios';
import { API_BASE_URL, TOKEN_KEY, USER_KEY } from '../utils/constants';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
  timeout: 10000,
});

// Request interceptor - Add token to requests
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem(TOKEN_KEY);
    
    console.log('=== API REQUEST DEBUG ===');
    console.log('Full URL:', config.baseURL + config.url);
    console.log('Method:', config.method?.toUpperCase());
    console.log('Token exists:', !!token);
    console.log('Token preview:', token ? token.substring(0, 20) + '...' : 'None');
    console.log('========================');
    
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
      console.log('Authorization header set with Bearer token');
    } else {
      console.log('No token found in localStorage');
    }
    
    return config;
  },
  (error) => {
    console.error('=== REQUEST INTERCEPTOR ERROR ===', error);
    return Promise.reject(error);
  }
);

// Response interceptor - Handle responses and errors
api.interceptors.response.use(
  (response) => {
    console.log('=== API RESPONSE SUCCESS ===');
    console.log('URL:', response.config.url);
    console.log('Status:', response.status);
    console.log('Data:', response.data);
    console.log('========================');
    return response;
  },
  (error) => {
    console.log('=== API RESPONSE ERROR ===');
    console.log('URL:', error.config?.url);
    console.log('Full URL:', error.config?.baseURL + error.config?.url);
    console.log('Status:', error.response?.status);
    console.log('Status Text:', error.response?.statusText);
    console.log('Error Message:', error.message);
    console.log('Response Data:', error.response?.data);
    
    // Network errors (CORS, connection refused, etc.)
    if (error.code === 'NETWORK_ERROR' || error.code === 'ECONNREFUSED') {
      console.log('Network error detected - check CORS and server connectivity');
    }
    
    // CORS specific errors
    if (error.message && error.message.includes('CORS')) {
      console.log('CORS error detected - check backend CORS configuration');
    }
    
    console.log('========================');
    
    // Handle authentication errors
    if (error.response?.status === 401 || error.response?.status === 403) {
      console.log('Authentication error detected, clearing tokens');
      localStorage.removeItem(TOKEN_KEY);
      localStorage.removeItem(USER_KEY);
      
      // Only redirect if not already on login page
      if (window.location.pathname !== '/login' && window.location.pathname !== '/') {
        console.log('Redirecting to login page');
        window.location.href = '/login';
      }
    }
    
    return Promise.reject(error);
  }
);

// Auth API endpoints
export const authAPI = {
  register: (userData) => api.post('/auth/register', userData),
  login: (credentials) => api.post('/auth/login', credentials),
  getProfile: () => api.get('/auth/profile'),
};

// Recipes API endpoints
export const recipesAPI = {
  getAllRecipes: () => api.get('/recipes'),
  getMyRecipes: () => api.get('/my-recipes'),
  getRecipeById: (id) => api.get(`/recipes/${id}`),
  createRecipe: (recipeData) => api.post('/recipes', recipeData),
  updateRecipe: (id, recipeData) => api.put(`/recipes/${id}`, recipeData),
  deleteRecipe: (id) => api.delete(`/recipes/${id}`),
  searchRecipes: (query) => api.get(`/recipes/search?q=${query}`),
};

// Health check endpoint to test connectivity
export const healthAPI = {
  check: () => api.get('/health'),
};

export default api;