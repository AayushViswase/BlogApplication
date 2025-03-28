import SideMenu from "./components/SideMenu";
import Navbar from "./components/NavBar.tsx";
import Posts from "./components/Posts";
function App() {
  return (
    <>
      <Navbar />
      <div className="flex">
        <SideMenu />
        <Posts />
      </div>
    </>
  );
}

export default App;
