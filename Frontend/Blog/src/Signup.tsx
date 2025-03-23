import { useState } from "react";
import {
  TextField,
  Button,
  Card,
  CardContent,
  Typography,
} from "@mui/material";

export default function Signup() {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    about: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    console.log("Form Submitted:", formData);
  };

  return (
    <div className="flex justify-center items-center h-screen bg-gray-100 rounded-xl ">
      <Card className="w-120 h-auto shadow-lg rounded-lg">
        <CardContent className="p-6">
          <Typography variant="h4" className="text-center mb-4 font-serif">
            User Form
          </Typography>
          <form onSubmit={handleSubmit} className="space-y-5">
            <TextField
              label="Name"
              name="name"
              value={formData.name}
              onChange={handleChange}
              fullWidth
              variant="outlined"
              className="bg-white rounded"
            />
            <TextField
              label="Email"
              name="email"
              type="email"
              value={formData.email}
              onChange={handleChange}
              fullWidth
              variant="outlined"
              className="bg-white rounded"
            />
            <TextField
              label="Password"
              name="password"
              type="password"
              value={formData.password}
              onChange={handleChange}
              fullWidth
              variant="outlined"
              className="bg-white rounded"
            />
            <TextField
              label="About"
              name="about"
              value={formData.about}
              onChange={handleChange}
              fullWidth
              multiline
              rows={3}
              variant="outlined"
              className="bg-white rounded"
            />
            <Button type="submit" variant="contained" color="primary" fullWidth>
              Submit
            </Button>
          </form>
        </CardContent>
      </Card>
    </div>
  );
}
