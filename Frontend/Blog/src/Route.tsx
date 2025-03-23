import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.tsx";
import About from "./pages/About.tsx";
import Login from "./pages/Login.tsx";
import SignUp from "./pages/Signup.tsx";
import Navbar from "./components/NavBar.tsx";
import { BrowserRouter, Route, Routes } from "react-router-dom";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<App />} />
        <Route path="/about" Component={() => <About />} />
        <Route path="/login" Component={() => <Login />} />
        <Route path="/signup" Component={() => <SignUp />} />
      </Routes>
    </BrowserRouter>
  </StrictMode>
);
