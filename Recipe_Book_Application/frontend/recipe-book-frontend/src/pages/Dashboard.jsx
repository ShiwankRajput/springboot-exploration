// src/pages/Dashboard.jsx
import { Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import AuthDebug from '../components/AuthDebug';

const Dashboard = () => {
  const { user } = useAuth();

  return (
    <div className="container mt-4">
      <div className="row">
        <div className="col-12">
          {/* Add AuthDebug component here, inside the return */}
          <AuthDebug />
          
          <div className="jumbotron bg-light p-5 rounded">
            <h1 className="display-4 text-primary">Welcome to RecipeBook! 🍳</h1>
            <p className="lead">
              {user ? (
                `Hello, ${user.username}! Discover, create, and share your favorite recipes.`
              ) : (
                'Discover, create, and share your favorite recipes with the world.'
              )}
            </p>
            <hr className="my-4" />
            
            <div className="row mt-5">
              <div className="col-md-4 mb-4">
                <div className="card h-100 border-0 shadow-sm">
                  <div className="card-body text-center">
                    <div className="text-primary mb-3" style={{ fontSize: '3rem' }}>📚</div>
                    <h5 className="card-title">Browse Recipes</h5>
                    <p className="card-text">Explore recipes from our community of food lovers.</p>
                    <Link to="/" className="btn btn-outline-primary">View All Recipes</Link>
                  </div>
                </div>
              </div>
              
              {user ? (
                <>
                  <div className="col-md-4 mb-4">
                    <div className="card h-100 border-0 shadow-sm">
                      <div className="card-body text-center">
                        <div className="text-primary mb-3" style={{ fontSize: '3rem' }}>👨‍🍳</div>
                        <h5 className="card-title">My Recipes</h5>
                        <p className="card-text">Manage your personal collection of recipes.</p>
                        <Link to="/my-recipes" className="btn btn-outline-primary">My Recipes</Link>
                      </div>
                    </div>
                  </div>
                  
                  <div className="col-md-4 mb-4">
                    <div className="card h-100 border-0 shadow-sm">
                      <div className="card-body text-center">
                        <div className="text-primary mb-3" style={{ fontSize: '3rem' }}>➕</div>
                        <h5 className="card-title">Add Recipe</h5>
                        <p className="card-text">Share your culinary creations with the community.</p>
                        <Link to="/add-recipe" className="btn btn-outline-primary">Add New Recipe</Link>
                      </div>
                    </div>
                  </div>
                </>
              ) : (
                <>
                  <div className="col-md-4 mb-4">
                    <div className="card h-100 border-0 shadow-sm">
                      <div className="card-body text-center">
                        <div className="text-primary mb-3" style={{ fontSize: '3rem' }}>🔐</div>
                        <h5 className="card-title">Join Us</h5>
                        <p className="card-text">Create an account to start sharing your recipes.</p>
                        <Link to="/register" className="btn btn-outline-primary">Register</Link>
                      </div>
                    </div>
                  </div>
                  
                  <div className="col-md-4 mb-4">
                    <div className="card h-100 border-0 shadow-sm">
                      <div className="card-body text-center">
                        <div className="text-primary mb-3" style={{ fontSize: '3rem' }}>🚀</div>
                        <h5 className="card-title">Get Started</h5>
                        <p className="card-text">Already have an account? Sign in to continue.</p>
                        <Link to="/login" className="btn btn-outline-primary">Login</Link>
                      </div>
                    </div>
                  </div>
                </>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;