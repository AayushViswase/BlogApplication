import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.tsx";
import About from "./pages/About.tsx";
import AuthForm from "./pages/AuthForm.tsx";
import { BrowserRouter, Route, Routes } from "react-router-dom";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<AuthForm />} />
        <Route path="/about" Component={() => <About />} />
        <Route path="/home" Component={() => <App />} />
      </Routes>
    </BrowserRouter>
  </StrictMode>
);
