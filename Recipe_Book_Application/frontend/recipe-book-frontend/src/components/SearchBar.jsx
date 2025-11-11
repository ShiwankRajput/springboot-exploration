export default function SearchBar({ searchTerm, setSearchTerm }) {
  return (
    <div className="row justify-content-center mb-5">
      <div className="col-md-8">
        <div className="position-relative">
          <input
            type="text"
            className="form-control form-control-lg border-0 shadow-sm rounded-pill ps-5"
            style={{background: '#f8f9fa'}}
            placeholder="Search recipes by title..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
          <span className="position-absolute top-50 start-0 translate-middle-y ms-4 text-muted">
            🔍
          </span>
        </div>
      </div>
    </div>
  );
}