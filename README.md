# LikeList 金融商品收藏系統

## 專案簡介

本專案是一個全端金融商品收藏管理系統，後端採用 Spring Boot + MySQL，前端採用 Vue 3 + Vite，支援收藏商品的新增、查詢、編輯、刪除，並提供下拉式選單選擇使用者、產品與扣款帳號。

---

## 專案結構

```
likelist/
├── src/
│   ├── main/java/com/ycc/likelist/         # Spring Boot 後端原始碼
│   ├── main/resources/                     # 資料庫設定、application.yml
│   └── test/                               # 測試
├── DB/                                     # SQL 腳本
├── likelist-frontend/                      # Vue 3 前端專案
│   ├── src/
│   │   ├── App.vue
│   │   ├── api.js
│   │   └── components/
│   │       ├── LikeListTable.vue
│   │       └── LikeForm.vue
│   ├── index.html
│   └── package.json
├── README.md
└── ...
```

---

## 本地開發啟動教學

### 1. 啟動 MySQL 並建立資料表

1. 安裝 MySQL 8+，建立一個名為 `likelist` 的資料庫。
2. 執行 `DB/setup_database.sql` 匯入資料表與測試資料。

### 2. 啟動 Spring Boot 後端

```bash
./mvnw spring-boot:run
```
或用 IDE 執行 `LikelistApplication.java`
- 預設 API 端點：http://localhost:8080/api

### 3. 啟動 Vue 3 前端

```bash
cd likelist-frontend
npm install
npm run dev
```
- 預設前端網址：http://localhost:5173（或 517x）


## API 文件

請參考 `API_DOCUMENTATION.md`，內含所有 API 規格、範例與測試資料。

---

## 安全性說明

### SQL Injection（SQL 注入）
- 本專案後端所有資料庫操作均採用 Spring Data JPA 或 JdbcTemplate 預編譯查詢（PreparedStatement），**不會有 SQL Injection 風險**。
- 嚴禁直接字串拼接 SQL，所有參數皆以 `?` 佔位符傳遞。

### XSS（跨站腳本攻擊）
- 前端 Vue 3 預設對所有插值內容自動 escape，**不會有 XSS 風險**。
- 專案未使用 v-html 綁定不可信內容，所有用戶輸入皆以純文字顯示。
- 如需顯示 HTML，建議搭配 DOMPurify 等套件進行過濾。

---