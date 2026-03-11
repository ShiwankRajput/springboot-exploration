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
    console.log('=== AUTH CONTEXT DEBUG ===');
    
    // List all localStorage items for debugging
    console.log('All localStorage items:');
    for (let i = 0; i < localStorage.length; i++) {
      const key = localStorage.key(i);
      console.log(`${key}: ${localStorage.getItem(key)}`);
    }
    
    const token = localStorage.getItem(TOKEN_KEY);
    const savedUser = localStorage.getItem(USER_KEY);
    
    console.log('Token key:', TOKEN_KEY);
    console.log('Token exists:', !!token);
    console.log('Token value (first 20 chars):', token ? token.substring(0, 20) + '...' : 'null');
    
    console.log('User key:', USER_KEY);
    console.log('User exists:', !!savedUser);
    console.log('User value:', savedUser);
    
    if (token && savedUser) {
      try {
        const userData = JSON.parse(savedUser);
        console.log('Parsed user data:', userData);
        setUser(userData);
        console.log('✅ User restored from localStorage');
      } catch (error) {
        console.error('❌ Error parsing saved user:', error);
        console.error('Raw savedUser string:', savedUser);
        clearAuthData();
      }
    } else {
      console.log('⚠️ No auth data found in localStorage');
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
      
      // Log the exact URL being called
      console.log('Login URL:', 'http://localhost:8080/api/recipeBook/auth/login');
      
      const response = await authAPI.login(credentials);
      
      console.log('📦 Full login response:', response);
      console.log('📦 Response status:', response.status);
      console.log('📦 Response headers:', response.headers);
      console.log('📦 Response data:', response.data);
      
      // Check the exact structure of the response
      console.log('📦 Response data keys:', Object.keys(response.data));
      
      // Try different possible response structures
      let token, username;
      
      if (response.data.token && response.data.username) {
        token = response.data.token;
        username = response.data.username;
      } else if (response.data.accessToken) {
        token = response.data.accessToken;
        username = response.data.username || credentials.username;
      } else if (response.data.jwt) {
        token = response.data.jwt;
        username = response.data.username || credentials.username;
      } else {
        // If no standard structure, log everything
        console.error('❌ Unexpected response structure:', response.data);
        return { 
          success: false, 
          message: 'Unexpected response format from server',
          data: response.data
        };
      }
      
      console.log('✅ Extracted token:', token ? token.substring(0, 20) + '...' : 'null');
      console.log('✅ Extracted username:', username);
      
      if (token && username) {
        // Store token and user info
        localStorage.setItem(TOKEN_KEY, token);
        localStorage.setItem(USER_KEY, JSON.stringify({ username }));
        setUser({ username });
        
        // Verify storage
        console.log('✅ Token stored:', !!localStorage.getItem(TOKEN_KEY));
        console.log('✅ User stored:', localStorage.getItem(USER_KEY));
        
        return { success: true, data: response.data };
      } else {
        console.error('❌ Invalid response: missing token or username');
        return { 
          success: false, 
          message: 'Invalid response from server',
          data: response.data
        };
      }
    } catch (error) {
      console.error('❌ Login error:', error);
      console.error('❌ Error response status:', error.response?.status);
      console.error('❌ Error response headers:', error.response?.headers);
      console.error('❌ Error response data:', error.response?.data);
      console.error('❌ Error message:', error.message);
      console.error('❌ Error config:', error.config);
      
      return { 
        success: false, 
        message: error.response?.data?.message || 
                 error.response?.data?.error || 
                 error.message || 
                 'Login failed',
        error: error
      };
    }
  };

  const register = async (userData) => {
    try {
      console.log('=== REGISTER ATTEMPT ===');
      console.log('User data:', userData);
      
      const response = await authAPI.register(userData);
      console.log('📦 Full register response:', response);
      console.log('📦 Response data:', response.data);
      console.log('📦 Response data keys:', Object.keys(response.data));
      
      let token, username;
      
      if (response.data.token && response.data.username) {
        token = response.data.token;
        username = response.data.username;
      } else if (response.data.accessToken) {
        token = response.data.accessToken;
        username = response.data.username || userData.username;
      } else {
        console.error('❌ Unexpected register response structure:', response.data);
        return { 
          success: false, 
          message: 'Unexpected response format from server',
          data: response.data
        };
      }
      
      if (token && username) {
        localStorage.setItem(TOKEN_KEY, token);
        localStorage.setItem(USER_KEY, JSON.stringify({ username }));
        setUser({ username });
        
        return { success: true, data: response.data };
      } else {
        return { 
          success: false, 
          message: 'Invalid response from server',
          data: response.data
        };
      }
    } catch (error) {
      console.error('❌ Registration error:', error);
      console.error('❌ Error response data:', error.response?.data);
      
      return { 
        success: false, 
        message: error.response?.data?.message || error.message || 'Registration failed',
        error: error
      };
    }
  };

  const logout = () => {
    console.log('=== LOGOUT ===');
    clearAuthData();
  };

  // Add a test function to directly call the API
  const testDirectAPI = async () => {
    try {
      console.log('=== DIRECT API TEST ===');
      
      // Test 1: Call the API directly without axios instance
      const testResponse = await fetch('http://localhost:8080/api/recipeBook/auth/health');
      console.log('Direct fetch health check:', await testResponse.text());
      
      // Test 2: Try login with fetch
      const loginData = {
        username: 'testuser',
        password: 'password123'
      };
      
      const loginResponse = await fetch('http://localhost:8080/api/recipeBook/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(loginData)
      });
      
      console.log('Direct fetch login status:', loginResponse.status);
      console.log('Direct fetch login response:', await loginResponse.json());
      
    } catch (error) {
      console.error('Direct API test error:', error);
    }
  };

  const value = {
    user,
    login,
    register,
    logout,
    loading,
    testDirectAPI
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
};