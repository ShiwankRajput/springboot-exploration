// src/components/DebugAuthTest.jsx
import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';

const DebugAuthTest = () => {
  const { login, register, testDirectAPI } = useAuth();
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: ''
  });
  const [result, setResult] = useState(null);

  const handleLogin = async () => {
    const res = await login({
      username: formData.username,
      password: formData.password
    });
    setResult(res);
  };

  const handleRegister = async () => {
    const res = await register({
      username: formData.username,
      email: formData.email,
      password: formData.password
    });
    setResult(res);
  };

  const handleDirectTest = async () => {
    await testDirectAPI();
  };

  const clearStorage = () => {
    localStorage.clear();
    window.location.reload();
  };

  return (
    <div style={{ 
      padding: '20px', 
      margin: '20px', 
      border: '2px solid #007bff',
      borderRadius: '10px',
      backgroundColor: '#f8f9fa'
    }}>
      <h3 style={{ color: '#007bff' }}>🔍 Auth Debug Panel</h3>
      
      <div style={{ marginBottom: '15px' }}>
        <input
          type="text"
          placeholder="Username"
          value={formData.username}
          onChange={(e) => setFormData({...formData, username: e.target.value})}
          style={{ margin: '5px', padding: '8px' }}
        />
        <input
          type="email"
          placeholder="Email (for register)"
          value={formData.email}
          onChange={(e) => setFormData({...formData, email: e.target.value})}
          style={{ margin: '5px', padding: '8px' }}
        />
        <input
          type="password"
          placeholder="Password"
          value={formData.password}
          onChange={(e) => setFormData({...formData, password: e.target.value})}
          style={{ margin: '5px', padding: '8px' }}
        />
      </div>
      
      <div style={{ marginTop: '10px' }}>
        <button 
          onClick={handleLogin}
          style={{ 
            margin: '5px', 
            padding: '10px 20px',
            backgroundColor: '#28a745',
            color: 'white',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer'
          }}
        >
          Test Login
        </button>
        
        <button 
          onClick={handleRegister}
          style={{ 
            margin: '5px', 
            padding: '10px 20px',
            backgroundColor: '#17a2b8',
            color: 'white',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer'
          }}
        >
          Test Register
        </button>
        
        <button 
          onClick={handleDirectTest}
          style={{ 
            margin: '5px', 
            padding: '10px 20px',
            backgroundColor: '#ffc107',
            color: 'black',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer'
          }}
        >
          Direct API Test
        </button>
        
        <button 
          onClick={clearStorage}
          style={{ 
            margin: '5px', 
            padding: '10px 20px',
            backgroundColor: '#dc3545',
            color: 'white',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer'
          }}
        >
          Clear Storage
        </button>
      </div>
      
      {result && (
        <div style={{ 
          marginTop: '20px', 
          padding: '15px',
          backgroundColor: result.success ? '#d4edda' : '#f8d7da',
          border: `1px solid ${result.success ? '#c3e6cb' : '#f5c6cb'}`,
          borderRadius: '5px'
        }}>
          <h4>{result.success ? '✅ Success' : '❌ Error'}</h4>
          <pre style={{ 
            backgroundColor: '#f8f9fa', 
            padding: '10px', 
            borderRadius: '5px',
            overflowX: 'auto'
          }}>
            {JSON.stringify(result, null, 2)}
          </pre>
        </div>
      )}
      
      <div style={{ marginTop: '20px' }}>
        <h4>Current localStorage:</h4>
        <pre style={{ 
          backgroundColor: '#e9ecef', 
          padding: '10px', 
          borderRadius: '5px',
          maxHeight: '200px',
          overflowY: 'auto'
        }}>
          {JSON.stringify(
            Object.fromEntries(
              Object.entries(localStorage).map(([key, value]) => [
                key, 
                key.includes('token') ? value.substring(0, 20) + '...' : value
              ])
            ), 
            null, 2
          )}
        </pre>
      </div>
    </div>
  );
};

export default DebugAuthTest;