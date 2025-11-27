// src/context/AuthContext.jsx
import React, { createContext, useState, useContext, useEffect } from 'react';
import { authAPI } from '../services/api';
import { TOKEN_KEY, USER_KEY } from '../utils/constants';

const AuthContext = createContext();

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    checkAuthStatus();
  }, []);

  const checkAuthStatus = () => {
    const token = localStorage.getItem(TOKEN_KEY);
    const savedUser = localStorage.getItem(USER_KEY);
    
    console.log('=== AUTH CONTEXT DEBUG ===');
    console.log('Token in localStorage:', !!token);
    console.log('User in localStorage:', savedUser);
    
    if (token && savedUser) {
      try {
        const userData = JSON.parse(savedUser);
        console.log('Restoring user:', userData);
        setUser(userData);
      } catch (error) {
        console.error('Error parsing saved user:', error);
        clearAuthData();
      }
    } else {
      console.log('No auth data found in localStorage');
    }
    setLoading(false);
    console.log('========================');
  };

  const clearAuthData = () => {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem(USER_KEY);
    setUser(null);
  };

  const login = async (credentials) => {
    try {
      console.log('=== LOGIN ATTEMPT ===');
      console.log('Credentials:', credentials);
      
      const response = await authAPI.login(credentials);
      const { token, username } = response.data;
      
      console.log('Login response:', response.data);
      
      if (token && username) {
        // Store token and user info
        localStorage.setItem(TOKEN_KEY, token);
        localStorage.setItem(USER_KEY, JSON.stringify({ username }));
        setUser({ username });
        
        console.log('Token stored:', !!localStorage.getItem(TOKEN_KEY));
        console.log('User stored:', !!localStorage.getItem(USER_KEY));
        
        return { success: true };
      } else {
        console.error('Invalid response: missing token or username');
        return { 
          success: false, 
          message: 'Invalid response from server' 
        };
      }
    } catch (error) {
      console.error('Login error:', error);
      console.error('Error response:', error.response?.data);
      
      return { 
        success: false, 
        message: error.response?.data?.message || error.message || 'Login failed' 
      };
    }
  };

  const register = async (userData) => {
    try {
      console.log('=== REGISTER ATTEMPT ===');
      console.log('User data:', userData);
      
      const response = await authAPI.register(userData);
      const { token, username } = response.data;
      
      console.log('Register response:', response.data);
      
      if (token && username) {
        localStorage.setItem(TOKEN_KEY, token);
        localStorage.setItem(USER_KEY, JSON.stringify({ username }));
        setUser({ username });
        
        console.log('Token stored:', !!localStorage.getItem(TOKEN_KEY));
        console.log('User stored:', !!localStorage.getItem(USER_KEY));
        
        return { success: true };
      } else {
        console.error('Invalid response: missing token or username');
        return { 
          success: false, 
          message: 'Invalid response from server' 
        };
      }
    } catch (error) {
      console.error('Registration error:', error);
      console.error('Error response:', error.response?.data);
      
      return { 
        success: false, 
        message: error.response?.data?.message || error.message || 'Registration failed' 
      };
    }
  };

  const logout = () => {
    console.log('=== LOGOUT ===');
    clearAuthData();
  };

  const value = {
    user,
    login,
    register,
    logout,
    loading
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
};