import DoubleArrowIcon from "@mui/icons-material/DoubleArrow";
import HomeIcon from "@mui/icons-material/Home";
import LogoutIcon from "@mui/icons-material/Logout";
import MenuOpenIcon from "@mui/icons-material/MenuOpen";
import SettingsIcon from "@mui/icons-material/Settings";
import Tooltip from "@mui/material/Tooltip";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import userImage from "../assets/image.png";
import InfoIcon from "@mui/icons-material/Info";
interface SideMenuProps {
  onToggle: (toggleState: boolean) => void;
}

const menuItems = [
  { id: 1, label: "Home", link: "/home", icon: <HomeIcon /> },
  { id: 3, label: "About", link: "/about", icon: <InfoIcon /> },
];

export default function SideMenu({ onToggle }: SideMenuProps) {
  const [toggle, setToggle] = useState(false);
  const navigate = useNavigate();

  const handleToggle = () => {
    setToggle((prev) => {
      const newToggle = !prev;
      onToggle(newToggle);
      return newToggle;
    });
  };

  const handleLogout = () => {
    sessionStorage.setItem("isAuthenticated", "false");
    navigate("/");
  };

  return (
    <div className={`flex flex-col justify-between h-screen bg-gray-700 text-white transition-all duration-500 ${toggle ? "w-16" : "w-64"}`}>
      {/* Toggle Button */}
      <div className="flex justify-end p-2">
        <button className="bg-gray-600 p-1 rounded-full hover:bg-gray-500 transition" onClick={handleToggle}>
          {toggle ? <MenuOpenIcon /> : <DoubleArrowIcon className="rotate-180" />}
        </button>
      </div>

      {/* Navigation Items */}
      <div className="mt-14 flex flex-col space-y-1 px-2">
        {menuItems.map((item) => (
          <Tooltip title={item.label  } placement="top"  disableHoverListener={!toggle}>
            <button
              key={item.id}
              onClick={() => navigate(item.link)}
              className={`flex items-center w-full px-4 py-2 hover:bg-gray-600 rounded-md transition ${toggle ? "justify-center" : "gap-3"}`}
            >
              <span className="min-w-[24px] flex justify-center items-center">{item.icon}</span>
              {!toggle && <span className="whitespace-nowrap">{item.label}</span>}
            </button>
          </Tooltip>
        ))}
      </div>

      {/* User Profile + Logout */}
      <div className="px-4 pb-6">
        <div className="flex items-center space-x-3 mb-4">
          <Tooltip title="Your Profile" placement="top">
            <img src={userImage} alt="User" className="rounded-full size-8 cursor-pointer" />
          </Tooltip>
          {!toggle && <span className="text-sm font-medium">Username</span>}
        </div>
        <Tooltip title="Settings" placement="top" disableHoverListener={!toggle}>
          <button
            className={`flex items-center ${toggle ? "justify-center" : "gap-2"} w-5/6 text-sm bg-gray-800 hover:bg-gray-600 px-3 py-2 rounded transition`}
          >
            <SettingsIcon fontSize="small" />
            {!toggle && <span>Settings</span>}
          </button>
        </Tooltip>
        <Tooltip title="Logout" placement="top" disableHoverListener={!toggle}>
          <button
            onClick={handleLogout}
            className={`mt-3 flex items-center ${toggle ? "justify-center" : "gap-2"} w-5/6 text-sm bg-red-600 hover:bg-red-700 px-3 py-2 rounded transition`}
          >
            <LogoutIcon fontSize="small" />
            {!toggle && <span>Logout</span>}
          </button>
        </Tooltip>
      </div>
    </div>
  );
}
