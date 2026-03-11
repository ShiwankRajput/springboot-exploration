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
    
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
      console.log('Authorization header set with Bearer token');
    } else {
      console.log('No token found in localStorage');
    }
    console.log('========================');
    
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
    console.log('========================');
    return response;
  },
  (error) => {
    console.log('=== API RESPONSE ERROR ===');
    console.log('URL:', error.config?.url);
    console.log('Status:', error.response?.status);
    console.log('Error Message:', error.message);
    
    // Only handle authentication errors for specific cases
    if (error.response?.status === 401 || error.response?.status === 403) {
      console.log('Authentication error detected');
      
      // Don't redirect if we're already on login page or it's an auth request
      const isAuthRequest = error.config?.url?.includes('/auth/');
      const isLoginPage = window.location.pathname === '/login';
      
      if (!isAuthRequest && !isLoginPage) {
        console.log('Clearing tokens and redirecting to login');
        localStorage.removeItem(TOKEN_KEY);
        localStorage.removeItem(USER_KEY);
        window.location.href = '/login';
      }
    }
    
    console.log('========================');
    return Promise.reject(error);
  }
);

// Auth API endpoints
export const authAPI = {
  register: (userData) => api.post('/auth/register', userData),
  login: (credentials) => api.post('/auth/login', credentials),
  getProfile: () => api.get('/auth/profile'),
};

// Recipes API endpoints - FIXED PATHS
export const recipesAPI = {
  getAllRecipes: () => api.get('/recipes'),
  getMyRecipes: () => api.get('/my-recipes'),
  getRecipeById: (id) => api.get(`/recipes/${id}`),
  createRecipe: (recipeData) => api.post('/recipes', recipeData),
  updateRecipe: (id, recipeData) => api.put(`/recipes/${id}`, recipeData),
  deleteRecipe: (id) => api.delete(`/recipes/${id}`),
};

// Health check endpoint to test connectivity
export const healthAPI = {
  check: () => api.get('/health'),
};

export default api;