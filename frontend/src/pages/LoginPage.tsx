import { useNavigate } from "react-router-dom";
import { useState } from "react";
import axiosClient from "../lib/axiosClient";

export default function LoginPage() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    // 入力チェック
    if (!email || !password) {
      setError("メールアドレスとパスワードを入力してください。");
      return;
    }

    try {
      // Spring Boot の /api/auth/login にPOST
      const res = await axiosClient.post<{ token: string }>("/auth/login", {
        email,
        password,
      });
      localStorage.setItem("token", res.data.token);


      // トークン保存
      localStorage.setItem("token", res.data.token);
      alert("ログイン成功！");
      navigate("/home");
    } catch (err: any) {
      console.error(err);
      setError(
        err.response?.data?.message || "ログインに失敗しました。"
      );
    }
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      {/* ログインフォーム */}
      <div className="bg-white p-8 rounded-2xl shadow-md w-full max-w-sm">
        <h1 className="text-2xl font-bold text-center mb-6">ログイン</h1>
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium mb-1 text-gray-700">
              メールアドレス
            </label>
            {/* メールアドレス入力 */}
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full border border-gray-300 rounded-lg p-2 focus:ring-2 focus:ring-blue-500 focus:outline-none"
            />
          </div>
          <div>
            <label className="block text-sm font-medium mb-1 text-gray-700">
              パスワード
            </label>
            {/* パスワード入力 */}
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full border border-gray-300 rounded-lg p-2 focus:ring-2 focus:ring-blue-500 focus:outline-none"
            />
          </div>

          {error && <p className="text-red-500 text-sm text-center">{error}</p>}

          <button
            type="submit"
            className="w-full bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 rounded-lg transition"
          >
            ログイン
          </button>
        </form>

        <p className="text-center text-sm text-gray-600 mt-4">
          アカウントをお持ちでない方は{" "}
          <span
            onClick={() => navigate("/signup")}
            className="text-blue-500 hover:underline cursor-pointer"
          >
            サインアップ
          </span>
        </p>
      </div>
    </div>
  );
}
