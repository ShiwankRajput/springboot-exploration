import { FaGithub, FaLinkedin, FaCode } from "react-icons/fa";

export default function About() {
  return (
    <div
      className="about-section d-flex align-items-center justify-content-center"
      style={{
        minHeight: "80vh",
        background: "linear-gradient(135deg, #3f2b96, #a8c0ff)",
        padding: "40px 20px",
      }}
    >
      <div
        className="container"
        style={{
          maxWidth: "900px",
        }}
      >
        <div
          className="card shadow-lg p-4"
          style={{
            borderRadius: "20px",
            background: "rgba(255,255,255,0.15)",
            backdropFilter: "blur(15px)",
            border: "1px solid rgba(255,255,255,0.25)",
            color: "white",
          }}
        >
          <h2 className="text-center fw-bold mb-4">
            About <span style={{ color: "#ffe082" }}>RecipeBook</span>
          </h2>

          <p className="lead" style={{ fontWeight: "400", lineHeight: "1.7" }}>
            RecipeBook is a modern recipe management platform designed to help you store, edit,
            search, and organize your favorite recipes easily. Built using{" "}
            <strong>React</strong> on the frontend and <strong>Spring Boot</strong> on the backend,
            it provides a smooth and responsive user experience.
          </p>

          <h4 className="mt-4" style={{ color: "#ffe082" }}>
            What You Can Do
          </h4>
          <ul style={{ lineHeight: "1.8", fontSize: "17px" }}>
            <li>Add your own recipes with ingredients and instructions</li>
            <li>Edit recipes anytime</li>
            <li>Search recipes by title using instant real-time filtering</li>
            <li>View recipes in a clean card-based layout</li>
            <li>Navigate seamlessly with fast routing</li>
          </ul>

          <h4 className="mt-4" style={{ color: "#ffe082" }}>
            Tech Used
          </h4>
          <div className="row text-center mt-3">
            <div className="col-md-4">
              <FaCode size={35} />
              <p className="mt-2">React.js</p>
            </div>
            <div className="col-md-4">
              <FaCode size={35} />
              <p className="mt-2">Spring Boot</p>
            </div>
            <div className="col-md-4">
              <FaCode size={35} />
              <p className="mt-2">MySQL</p>
            </div>
          </div>

          <h4 className="mt-4" style={{ color: "#ffe082" }}>
            Connect With Developer
          </h4>

          <div className="d-flex justify-content-center mt-3 gap-4">
            <a
              href="https://github.com/ShiwankRajput"
              target="_blank"
              rel="noopener noreferrer"
              style={{ color: "white", textDecoration: "none" }}
            >
              <FaGithub size={32} />
            </a>

            <a
              href="https://www.linkedin.com/in/shiwank-kumar-945490289/"
              target="_blank"
              rel="noopener noreferrer"
              style={{ color: "white", textDecoration: "none" }}
            >
              <FaLinkedin size={32} />
            </a>
          </div>

          <p className="text-center mt-4 mb-0" style={{ opacity: 0.8 }}>
            © 2025 RecipeBook — Built with ❤️ for learning & productivity.
          </p>
        </div>
      </div>
    </div>
  );
}
