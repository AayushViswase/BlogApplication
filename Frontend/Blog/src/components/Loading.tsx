import { CircularProgress } from "@mui/material";

export default function Loading({ size = 80 }) {
  return (
    <div className="flex justify-center items-center h-screen">
      <CircularProgress size={size} />
    </div>
  );
}
