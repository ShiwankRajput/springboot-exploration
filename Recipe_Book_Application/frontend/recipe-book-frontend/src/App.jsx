// src/App.jsx
import { Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import Navbar from './components/Navbar';
import ProtectedRoute from './components/ProtectedRoutes';
import Login from './pages/Login';
import Register from './pages/Register';
import Dashboard from './pages/Dashboard';
import AllRecipes from './pages/AllRecipes';
import MyRecipes from './pages/MyRecipes';
import AddRecipe from './pages/AddRecipe';
import './App.css';

function App() {
  return (
    <AuthProvider>
      <div className="App">
        <Navbar />
        <main>
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/recipes" element={<AllRecipes />} />
            
            {/* Protected Routes */}
            <Route 
              path="/my-recipes" 
              element={
                <ProtectedRoute>
                  <MyRecipes />
                </ProtectedRoute>
              } 
            />
            <Route 
              path="/add-recipe" 
              element={
                <ProtectedRoute>
                  <AddRecipe />
                </ProtectedRoute>
              } 
            />
            
            {/* Redirect to home for unknown routes */}
            <Route path="*" element={<Navigate to="/" />} />
          </Routes>
        </main>
      </div>
    </AuthProvider>
  );
}

export default App;