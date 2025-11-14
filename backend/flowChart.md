``` mermaid

%%{init: {
  "theme": "base",
  "themeVariables": {
    "fontSize": "24px",
    "fontFamily": "Inter, Arial, sans-serif"
  },
  "flowchart": {
    "diagramPadding": 40,
    "nodeSpacing": 80,
    "rankSpacing": 90,
    "htmlLabels": true,
    "curve": "basis"
  }
}}%%

flowchart TB

%% =======================
%% Presentation 層
%% =======================
subgraph presentation["🟦 Presentation 層"]
direction TB
  A1["AuthController<br>TaskController（リクエスト受付）"]
  A2["GlobalExceptionHandler（例外ハンドリング）"]
end

%% =======================
%% Application 層
%% =======================
subgraph application["🟨 Application 層"]
direction TB
  B1["AuthService<br>CreateTaskService<br>GetTasksService<br>UpdateTaskService<br>DeleteTaskService"]
  B2["port.in（UseCase Interface）<br>AuthUseCase, GetTasksUseCase ..."]
  B3["port.out（外部依頼インターフェース）<br>UserRepositoryPort, TaskRepositoryPort, TokenProviderPort"]
end

%% =======================
%% Domain 層
%% =======================
subgraph domain["🟩 Domain 層"]
direction TB
  C1["User, UserId<br>Task, TaskId<br>ResourceNotFoundException"]
end

%% =======================
%% Infrastructure 層
%% =======================
subgraph infrastructure["🟧 Infrastructure 層"]
direction TB
  D1["RepositoryAdapter<br>(UserRepositoryAdapter / TaskRepositoryAdapter)"]
  D2["JwtProvider<br>(TokenProviderPortの実装)<br>→ JWT生成 / 検証"]
  D3["JwtAuthFilter<br>→ リクエストからToken抽出し認証にセット"]
  D4["SecurityConfig<br>→ Spring Security設定 / フィルタ登録"]
  D5["JpaRepository<br>(UserJpaRepository / TaskJpaRepository)"]
  D6["JpaEntity<br>(UserJpaEntity / TaskJpaEntity)"]
  D7["CorsConfig・SwaggerConfig"]
end

%% =======================
%% 通常フロー（外→内→外）🟦
%% =======================
A1 --> B1
B1 --> C1
B1 --> B3
B3 --> D1
B3 --> D2
D1 --> D5
D5 --> D6

%% =======================
%% JWT認証フロー 🟥
%% =======================
A1 --> D3
D3 --> D2
D2 --> D3
D3 --> A1
D4 --- D3

%% =======================
%% 依存関係（設計ルール）🟠 黒文字＋極太点線
%% =======================
D1 -.->|"<b>implements</b>"| B3
D2 -.->|"<b>implements</b>"| B3
B1 -.->|"<b>implements</b>"| B2

%% =======================
%% 線のスタイル（太く統一）
%% =======================
linkStyle 0,1,2,3,4,5 stroke:#2196F3,stroke-width:6px
linkStyle 6,7,8,9 stroke:#E53935,stroke-width:6px
linkStyle 10,11,12 stroke:#FF8C00,stroke-width:8px,stroke-dasharray:10 6

%% =======================
%% 層デザイン（枠も太く）
%% =======================
classDef pres fill:#e0e8ff,color:#000,stroke:#3f51b5,stroke-width:6px,font-weight:bold
classDef app fill:#fff9d9,color:#000,stroke:#fbc02d,stroke-width:6px,font-weight:bold
classDef domain fill:#dcf8e8,color:#000,stroke:#43a047,stroke-width:6px,font-weight:bold
classDef infra fill:#fff2cc,color:#000,stroke:#f57c00,stroke-width:6px,font-weight:bold

class presentation pres
class application app
class domain domain
class infrastructure infra

```