import { Suspense, useState } from "react";
import Loading from "../components/Loading";
import { useNavigate } from "react-router-dom";
import { login$, signup$ } from "../services/api.service";
import { Alert, Snackbar } from "@mui/material";

const AuthForm = () => {
  const [isSignup, setIsSignup] = useState(false);
  const [signUpFormData, setSignUpFormData] = useState({
    name: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const [loginFormData, setLoginFormData] = useState({
    email: "",
    password: "",
  });

  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState("");
  const [snackbarSeverity, setSnackbarSeverity] = useState<"success" | "error">("success");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleSignUpChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setSignUpFormData({ ...signUpFormData, [e.target.name]: e.target.value });
  };

  const handleLoginChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setLoginFormData({ ...loginFormData, [e.target.name]: e.target.value });
  };

  const handleSignup = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);

    try {
      await signup$(signUpFormData);
      setSnackbarMessage("Signup successful! Please login");
      setSnackbarSeverity("success");
      setOpenSnackbar(true);
      setIsSignup(false);
      setSignUpFormData({
        name: "",
        email: "",
        password: "",
        confirmPassword: "",
      });
    } catch (error) {
      setSnackbarMessage("Signup failed. Please try again.");
      setSnackbarSeverity("error");
      setOpenSnackbar(true);
      console.error("Signup failed:", error);
    } finally {
      setLoading(false);
    }
  };

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    try {
      // Replace this with actual login API call
      await login$(loginFormData);
      setLoading(false);
      setSnackbarMessage("Login successful! Redirecting...");
      setSnackbarSeverity("success");
      setOpenSnackbar(true);
      setTimeout(() => {
        navigate("/home");
      }, 2000);
    } catch (error) {
      setSnackbarMessage("Login failed. Please try again.");
      setSnackbarSeverity("error");
      setOpenSnackbar(true);
      setLoading(false);
      console.error("Login failed:", error);
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gradient-to-r from-gray-400 via-gray-300 to-gray-200">
      <Suspense fallback={<Loading />}>
        {loading ? (
          <div className="w-full flex justify-center items-center">
            <Loading size={80} />
          </div>
        ) : (
          <div className="relative w-96 bg-white p-6 rounded-2xl shadow-lg overflow-hidden">
            <h2 className="text-center text-2xl font-semibold transition-all duration-500">{isSignup ? "Signup Form" : "Login Form"}</h2>

            <div className="relative flex items-center mt-6 border border-gray-300 rounded-xl overflow-hidden">
              <button
                className={`w-1/2 py-2 text-center transition-colors duration-500 ${!isSignup ? "bg-gray-600 text-white" : "text-gray-800"}`}
                onClick={() => setIsSignup(false)}
              >
                Login
              </button>
              <button
                className={`w-1/2 py-2 text-center transition-colors duration-500 ${isSignup ? "bg-gray-600 text-white" : "text-gray-800"}`}
                onClick={() => setIsSignup(true)}
              >
                Signup
              </button>
            </div>

            <div className="overflow-hidden mt-6">
              <div
                className="flex w-[200%] transition-transform duration-500 ease-in-out"
                style={{ transform: isSignup ? "translateX(-50%)" : "translateX(0%)" }}
              >
                {/* Login Form */}
                <form className="w-1/2 p-4" onSubmit={handleLogin}>
                  <div className="space-y-4">
                    <input
                      type="email"
                      name="email"
                      placeholder="Email Address"
                      className="w-full p-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-gray-600"
                      required
                      value={loginFormData.email}
                      onChange={handleLoginChange}
                    />
                    <input
                      type="password"
                      name="password"
                      placeholder="Password"
                      className="w-full p-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-gray-600"
                      required
                      value={loginFormData.password}
                      onChange={handleLoginChange}
                    />
                    <div className="text-right text-sm text-gray-600 hover:underline cursor-pointer">Forgot password?</div>
                    <button
                      type="submit"
                      className="w-full py-3 text-lg font-medium text-white bg-gray-700 rounded-lg hover:bg-gray-800 transition-all duration-300"
                    >
                      Login
                    </button>
                  </div>
                </form>

                {/* Signup Form */}
                <form className="w-1/2 p-4" onSubmit={handleSignup}>
                  <div className="space-y-4">
                    <input
                      type="text"
                      name="name"
                      placeholder="Full Name"
                      className="w-full p-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-gray-600"
                      required
                      value={signUpFormData.name}
                      onChange={handleSignUpChange}
                    />
                    <input
                      type="email"
                      name="email"
                      placeholder="Email Address"
                      className="w-full p-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-gray-600"
                      required
                      value={signUpFormData.email}
                      onChange={handleSignUpChange}
                    />
                    <input
                      type="password"
                      name="password"
                      placeholder="Password"
                      className="w-full p-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-gray-600"
                      required
                      value={signUpFormData.password}
                      onChange={handleSignUpChange}
                    />
                    <input
                      type="password"
                      name="confirmPassword"
                      placeholder="Confirm Password"
                      className="w-full p-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-gray-600"
                      required
                      value={signUpFormData.confirmPassword}
                      onChange={handleSignUpChange}
                    />
                    <button
                      type="submit"
                      className="w-full py-3 text-lg font-medium text-white bg-gray-700 rounded-lg hover:bg-gray-800 transition-all duration-300"
                    >
                      Signup
                    </button>
                  </div>
                </form>
              </div>

              <div className="text-center mt-4">
                {isSignup ? (
                  <span>
                    Already a member?{" "}
                    <button className="text-gray-600" onClick={() => setIsSignup(false)}>
                      Login now
                    </button>
                  </span>
                ) : (
                  <span>
                    Not a member?{" "}
                    <button className="text-gray-600" onClick={() => setIsSignup(true)}>
                      Signup now
                    </button>
                  </span>
                )}
              </div>
            </div>
          </div>
        )}
      </Suspense>
      <Snackbar open={openSnackbar} autoHideDuration={3000} onClose={() => setOpenSnackbar(false)}>
        <Alert onClose={() => setOpenSnackbar(false)} severity={snackbarSeverity} variant="filled">
          {snackbarMessage}
        </Alert>
      </Snackbar>
    </div>
  );
};

export default AuthForm;
