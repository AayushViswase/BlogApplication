import MenuOpenIcon from "@mui/icons-material/MenuOpen";
import DoubleArrowIcon from "@mui/icons-material/DoubleArrow";
import { useState } from "react";
const menuItems = [
  { id: 1, label: "Articles", link: "/about" },
  { id: 2, label: "Profiles", link: "/profiles" },
  { id: 3, label: "Tester", link: "/tester" },
  { id: 4, label: "Comments", link: "/Comments" },
];

interface SideMenuProps {
  onToggle: (toggleState: boolean) => void;
}

export default function SideMenu({ onToggle }: SideMenuProps) {
  const [toggle, setToggle] = useState(false);

  const handleToggle = () => {
    setToggle(!toggle);
    onToggle(!toggle); // Notify parent about the toggle change
  };

  return (
    // <div className="w-1/4 p-4 bg-gray-200">
    <div className={`p-4 h-screen px-4 pt-8 pb-4 bg-light justify-between flex-col bg-gray-400 ${toggle ? "w-15" : "w-50"} duration-500`}>
      <div className="flex flex-col">
        <div className="flex flex-col relative h-full">
          <div className="flex items-center " />
          <button className=" absolute right-0 top-1/2 -translate-y-1/2" onClick={handleToggle}>
            {toggle ? <MenuOpenIcon /> : <DoubleArrowIcon className="rotate-180" />}
          </button>
        </div>
      </div>

      <div className="flex flex-col items-start px-5 my-10">
        {menuItems.map((item) => (
          <div key={item.id} className={`flex cursor-pointer hover:bg-green-200 w-full overflow-hidden whitespace-nowrap ${toggle ? "hidden" : ""}`}>
            <a href={item.link} className="flex py-2 px-3 w-full ">
              <div>{!toggle && <span className="text-md font-medium text-text-light">{item.label}</span>}</div>
            </a>
          </div>
        ))}
      </div>
    </div>
  );
}
