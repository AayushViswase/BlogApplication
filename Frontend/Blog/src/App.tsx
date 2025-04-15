import SideMenu from "./components/SideMenu";
import Navbar from "./components/NavBar.tsx";
import Posts from "./components/Posts";
import { useState } from "react";

function App() {
  const [menuOpen, setMenuOpen] = useState(true);
  const handleMenuToggle = (isOpen: boolean) => {
    setMenuOpen(isOpen);
  };
  return (
    <>
      <Navbar />
      <div className="flex">
        <SideMenu onToggle={handleMenuToggle} />
        <div className={`transition-all duration-500 ${menuOpen ? "w-5/6" : "w-full"}`}>
          <Posts />
        </div>
      </div>
    </>
  );
}

export default App;
