import { useState, Suspense } from "react";
import {
  TextField,
  Button,
  Card,
  CardContent,
  Typography,
  Snackbar,
  Alert,
  CircularProgress,
} from "@mui/material";
import { useNavigate } from "react-router-dom";
import { signup$ } from "../services/api.service";
import Loading from "../components/Loading";

export default function Signup() {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    about: "",
  });

  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState("");
  const [snackbarSeverity, setSnackbarSeverity] = useState<"success" | "error">(
    "success"
  );
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    try {
      await signup$(formData);
      setSnackbarMessage("Signup successful! Redirecting...");
      setSnackbarSeverity("success");
      setOpenSnackbar(true);

      setTimeout(() => {
        navigate("/login");
      }, 2000);
    } catch (error) {
      setSnackbarMessage("Signup failed. Please try again.");
      setSnackbarSeverity("error");
      setOpenSnackbar(true);
      setLoading(false);
      console.error("Signup failed:", error);
    }
  };

  return (
    <div className="flex justify-center items-center min-h-screen bg-gray-100 p-4">
      <Suspense fallback={<Loading />}>
        {loading ? (
          <div className="w-full flex justify-center items-center">
            <Loading size={80} />
          </div>
        ) : (
          <Card className="w-full max-w-md shadow-lg rounded-lg p-6 overflow-auto">
            <CardContent>
              <Typography variant="h4" className="text-center mb-4">
                User Signup
              </Typography>

              <form onSubmit={handleSubmit} className="space-y-4">
                <TextField
                  label="Name"
                  name="name"
                  value={formData.name}
                  onChange={handleChange}
                  fullWidth
                  variant="outlined"
                />
                <TextField
                  label="Email"
                  name="email"
                  type="email"
                  value={formData.email}
                  onChange={handleChange}
                  fullWidth
                  variant="outlined"
                />
                <TextField
                  label="Password"
                  name="password"
                  type="password"
                  value={formData.password}
                  onChange={handleChange}
                  fullWidth
                  variant="outlined"
                />
                <TextField
                  label="About"
                  name="about"
                  value={formData.about}
                  onChange={handleChange}
                  fullWidth
                  multiline
                  rows={3}
                  variant="outlined"
                />
                <Button
                  type="submit"
                  variant="contained"
                  color="primary"
                  fullWidth
                  disabled={loading}
                >
                  {loading ? <CircularProgress size={24} /> : "Sign Up"}
                </Button>
              </form>
            </CardContent>
          </Card>
        )}
      </Suspense>

      {/* Snackbar Notification */}
      <Snackbar
        open={openSnackbar}
        autoHideDuration={3000}
        onClose={() => setOpenSnackbar(false)}
      >
        <Alert
          onClose={() => setOpenSnackbar(false)}
          severity={snackbarSeverity}
          variant="filled"
        >
          {snackbarMessage}
        </Alert>
      </Snackbar>
    </div>
  );
}
