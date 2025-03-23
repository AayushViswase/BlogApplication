/* eslint-disable @typescript-eslint/no-explicit-any */
import { apiService } from "./helper";
export const signup$ = (formData: any) => {
  //   return apiService.post("/api/user/", formData);
  // dummy data
  console.log(formData);

  return new Promise((resolve) => {
    resolve({ data: { message: "Signup successful" } });
  });
};
export const login$ = (formData: any) => {
  return apiService.post("/api/login", formData);
};
