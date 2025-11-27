// src/components/AuthDebug.jsx
import { useAuth } from '../context/AuthContext';

const AuthDebug = () => {
  const { user, loading } = useAuth();
  
  const checkLocalStorage = () => {
    const token = localStorage.getItem('recipeBookToken');
    const userData = localStorage.getItem('recipeBookUser');
    
    console.log('=== MANUAL LOCALSTORAGE CHECK ===');
    console.log('Token:', token);
    console.log('User:', userData);
    console.log('========================');
    
    return { token, userData };
  };

  const storage = checkLocalStorage();

  return (
    <div className="alert alert-warning">
      <h6>🔧 Authentication Debug Info</h6>
      <p><strong>Auth Context User:</strong> {user ? JSON.stringify(user) : 'Not logged in'}</p>
      <p><strong>Auth Context Loading:</strong> {loading ? 'Yes' : 'No'}</p>
      <p><strong>LocalStorage Token:</strong> {storage.token ? 'Exists' : 'Missing'}</p>
      <p><strong>LocalStorage User:</strong> {storage.userData ? storage.userData : 'Missing'}</p>
      <button 
        className="btn btn-sm btn-outline-secondary mt-2"
        onClick={checkLocalStorage}
      >
        Refresh Check
      </button>
    </div>
  );
};

export default AuthDebug;