export default function About() {
  return (
    <div className="min-vh-100 bg-light py-5">
      <div className="container">
        <div className="row justify-content-center">
          <div className="col-lg-8">
            <div className="card shadow-lg border-0">
              <div className="card-header bg-white py-4 border-0">
                <div className="text-center">
                  <div className="bg-info rounded-circle d-inline-flex align-items-center justify-content-center mb-3" 
                       style={{width: '60px', height: '60px'}}>
                    <span className="text-white fs-4">ℹ️</span>
                  </div>
                  <h2 className="text-dark fw-bold mb-0">About RecipeBook</h2>
                </div>
              </div>
              
              <div className="card-body p-5">
                <p className="lead text-muted mb-4 text-center">
                  A modern recipe management platform built with React and Spring Boot
                </p>

                <div className="row mb-4">
                  <div className="col-md-6">
                    <h5 className="text-dark fw-bold mb-3">Features</h5>
                    <ul className="list-unstyled">
                      <li className="mb-2 d-flex align-items-center">
                        <span className="text-success me-2">✓</span>
                        Add and edit recipes
                      </li>
                      <li className="mb-2 d-flex align-items-center">
                        <span className="text-success me-2">✓</span>
                        Real-time search
                      </li>
                      <li className="mb-2 d-flex align-items-center">
                        <span className="text-success me-2">✓</span>
                        Clean, modern interface
                      </li>
                    </ul>
                  </div>
                  <div className="col-md-6">
                    <h5 className="text-dark fw-bold mb-3">Tech Stack</h5>
                    <ul className="list-unstyled">
                      <li className="mb-2">React.js</li>
                      <li className="mb-2">Spring Boot</li>
                      <li className="mb-2">MySQL</li>
                    </ul>
                  </div>
                </div>

                <div className="text-center mt-5 pt-3 border-top">
                  <p className="text-muted mb-0">
                    © 2025 RecipeBook — Built for culinary creativity
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}