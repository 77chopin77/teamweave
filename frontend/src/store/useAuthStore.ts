import { create } from "zustand";
import axiosClient from "../lib/axiosClient";

interface AuthState {
  username: string;
  password: string;
  token: string | null;
  setUsername: (name: string) => void;
  setPassword: (pw: string) => void;
  login: () => Promise<void>;
}

export const useAuthStore = create<AuthState>((set, get) => ({
  username: "",
  password: "",
  token: null,

  // 入力変更ハンドラ
  setUsername: (name) => set({ username: name }),
  setPassword: (pw) => set({ password: pw }),

  // Spring Boot へログインリクエスト
  login: async () => {
    const { username, password } = get();
    try {
      const res = await axiosClient.post<{ token: string }>("/auth/login", {
        email: username,
        password,
      });

      const token = res.data.token;
      localStorage.setItem("token", token);
      set({ token });
      alert("ログイン成功！\nToken: " + token);
    } catch (err: any) {
      alert("ログイン失敗: " + (err.response?.data?.message || err.message));
    }
  },
}));
