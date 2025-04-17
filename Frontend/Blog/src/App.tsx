import SideMenu from "./components/SideMenu";
import Posts from "./components/PostCards";
import { useState } from "react";

function App() {
  const [menuOpen, setMenuOpen] = useState(true);

  const handleMenuToggle = (isOpen: boolean) => {
    setMenuOpen(isOpen);
  };

  return (
    <div className="flex flex-col h-screen overflow-hidden">
      {/* <Navbar /> */}
      <div className="flex flex-1 overflow-hidden">
        <SideMenu onToggle={handleMenuToggle} />
        <div className={`transition-all duration-500 flex-1 overflow-auto ${menuOpen ? "ml-0" : "ml-0"}`}>
          <Posts />
        </div>
      </div>
    </div>
  );
}

export default App;
