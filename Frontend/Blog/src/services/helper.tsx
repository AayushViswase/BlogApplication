import axios from "axios";

export const BASE_URL = "http://localhost:8088";
export const apiService = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});
