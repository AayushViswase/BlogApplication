import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.tsx";
import About from "./About";
import Login from "./Login";
import SignUp from "./Signup";
import { BrowserRouter, Route, Routes } from "react-router-dom";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" Component={() => <About />} />
        <Route path="/login" Component={() => <Login />} />
        <Route path="/signup" Component={() => <SignUp />} />
      </Routes>
      <App />
    </BrowserRouter>
  </StrictMode>
);
