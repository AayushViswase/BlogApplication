/* eslint-disable @typescript-eslint/no-explicit-any */
import { apiService } from "./helper";
export const signup$ = (formData: any) => {
  const { name, email, password } = formData;
  const data = {
    name,
    email,
    password,
    about: "asdas",
  };
  return apiService.post("/api/user/", data);
  // dummy data
  // console.log(formData);

  return new Promise((resolve) => {
    resolve({ data: { message: "Signup successful" } });
  });
};
export const login$ = (formData: any) => {
  const data = {
    username: formData.email,
    password: formData.password,
  };
  return apiService.get("/api/user/login", { params: data });
};
