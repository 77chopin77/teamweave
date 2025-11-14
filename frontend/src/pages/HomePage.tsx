import { useEffect, useState } from "react";
import axiosClient from "../lib/axiosClient";
import { useNavigate } from "react-router-dom";

type User = {
  id: string;
  email: string;
  username?: string;
};

export default function HomePage() {
  const [user, setUser] = useState<User | null>(null);
  const navigate = useNavigate();

  // ページ表示時にログインユーザーを取得
  useEffect(() => {
    const fetchUser = async () => {
      try {
        const res = await axiosClient.get<User>("/user/me");
        setUser(res.data);
      } catch (err) {
        console.error(err);
      }
    };

    fetchUser();
  }, []);

  {/* ログイン中ユーザー情報 */}
  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-50">
      <div className="bg-white shadow-md rounded-xl p-8 w-96 text-center">
        <h1 className="text-2xl font-bold mb-4">ホーム画面</h1>
        {user ? (
          <>
          {/* ログアウト処理 */} 
            <p className="text-gray-700 mb-2">ようこそ、{user.username ?? user.email} さん！</p>
            <p className="text-sm text-gray-500 mb-6">ユーザーID: {user.id}</p>
            <button
              onClick={() => {
                localStorage.removeItem("token");
                navigate("/login");
              }}
              className="bg-red-500 hover:bg-red-600 text-white px-4 py-2 rounded-lg"
            >
              ログアウト
            </button>
          </>
        ) : (
          <p>読み込み中...</p>
        )}
      </div>
    </div>
  );
}
