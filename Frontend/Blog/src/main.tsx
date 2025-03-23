import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.tsx";
import About from "./About.tsx";
import Login from "./Login.tsx";
import SignUp from "./Signup.tsx";
import Navbar from "./NavBar.tsx";
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
