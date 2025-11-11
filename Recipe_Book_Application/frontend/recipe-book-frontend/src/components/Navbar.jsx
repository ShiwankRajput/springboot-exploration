import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark shadow">
      <div className="container">
        <Link className="navbar-brand fw-bold fs-3 d-flex align-items-center" to="/">
          <span className="bg-success rounded-circle p-2 me-2 d-flex align-items-center justify-content-center" style={{width: '40px', height: '40px'}}>
            <span className="text-white">R</span>
          </span>
          RecipeBook
        </Link>

        <button
          className="navbar-toggler border-0"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navItems"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="navItems">
          <ul className="navbar-nav ms-auto">
            <li className="nav-item mx-2">
              <Link className="nav-link fw-medium px-3 py-2 rounded" to="/">
                Home
              </Link>
            </li>
            <li className="nav-item mx-2">
              <Link className="nav-link fw-medium px-3 py-2 rounded" to="/add">
                Add Recipe
              </Link>
            </li>
            <li className="nav-item mx-2">
              <Link className="nav-link fw-medium px-3 py-2 rounded" to="/about">
                About
              </Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}