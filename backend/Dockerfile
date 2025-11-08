# Java17 の軽量イメージを使用
FROM eclipse-temurin:17-jdk-jammy

# 作業ディレクトリを指定
WORKDIR /app

# ビルド済みJARファイルをコピー（実行可能JARのみ）
COPY build/libs/teamweave-0.0.1-SNAPSHOT.jar app.jar

# 外部に公開するポートを指定
EXPOSE 8080

# 起動コマンド
ENTRYPOINT ["java", "-jar", "app.jar"]
