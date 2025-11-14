import React from "react";
import { Navigate } from "react-router-dom";

type Props = {
  children: React.ReactNode; // 子コンポーネントを受け取る
};

export default function ProtectedRoute({ children }: Props) {
  // ローカルストレージからJWTを取得
  const token = localStorage.getItem("token");

  // トークンがない場合ログインページへ強制遷移
  if (!token) {
    return <Navigate to="/login" replace />;
  }

  // 認証済みの場合、子コンポーネントを表示
  return <>{children}</>;
}
