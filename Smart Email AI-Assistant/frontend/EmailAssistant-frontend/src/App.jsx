import { useState } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css'; // We'll add custom styles here

function App() {
  const [emailContent, setEmailContent] = useState('');
  const [tone, setTone] = useState('');
  const [generatedReply, setGeneratedReply] = useState('');
  const [loading, setLoading] = useState(false);

  const handleSubmit = async () => {
    if (!emailContent) return;

    setLoading(true);
    try {
      const response = await axios.post('http://localhost:8080/api/email/generate', {
        emailContent,
        tone
      });

      setGeneratedReply(
        typeof response.data === 'string'
          ? response.data
          : JSON.stringify(response.data, null, 2)
      );
    } catch (error) {
      console.error('Error generating reply:', error);
      setGeneratedReply('Failed to generate reply. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container py-5">
      <div className="text-center mb-5">
        <h1 className="fw-bold display-5 text-primary">Smart Email Assistant</h1>
        <p className="text-secondary fs-5">Generate professional, friendly, or casual replies instantly.</p>
      </div>

      <div className="row g-4">
        {/* Input Card */}
        <div className="col-12 col-lg-6">
          <div className="card shadow-sm p-4 h-100 border-primary border-2">
            <h5 className="card-title mb-3 text-primary">Original Email</h5>
            <textarea
              className="form-control mb-3 fs-6"
              rows="6"
              placeholder="Paste your email here..."
              value={emailContent}
              onChange={(e) => setEmailContent(e.target.value)}
              style={{ fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif" }}
            />
            <select
              className="form-select mb-3 fs-6"
              value={tone}
              onChange={(e) => setTone(e.target.value)}
            >
              <option value="">Select Tone (Optional)</option>
              <option value="professional">Professional</option>
              <option value="casual">Casual</option>
              <option value="friendly">Friendly</option>
            </select>
            <button
              className="btn btn-primary w-100 fw-semibold"
              onClick={handleSubmit}
              disabled={!emailContent || loading}
            >
              {loading ? (
                <div className="spinner-border spinner-border-sm text-light" role="status">
                  <span className="visually-hidden">Loading...</span>
                </div>
              ) : (
                'Generate Reply'
              )}
            </button>
          </div>
        </div>

        {/* Output Card */}
        <div className="col-12 col-lg-6">
          <div className="card shadow-sm p-4 h-100 border-success border-2">
            <h5 className="card-title mb-3 text-success">Generated Reply</h5>
            <textarea
              className="form-control mb-3 fs-6"
              rows="6"
              value={generatedReply}
              readOnly
              placeholder="Your generated reply will appear here..."
              style={{ fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif" }}
            />
            <button
              className="btn btn-outline-success w-100 fw-semibold"
              onClick={() => navigator.clipboard.writeText(generatedReply)}
              disabled={!generatedReply}
            >
              Copy to Clipboard
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
