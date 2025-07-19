# 喜好金融商品管理 API 文檔

## API 端點

### 1. 新增喜好金融商品
**POST** `/api/like`

**請求範例：**
```json
{
  "userId": "A123456789",
  "productNo": "A001",
  "orderCount": 5,
  "account": "12345678"
}
```

**說明：**
- `userId`: 使用者ID
- `productNo`: 產品編號
- `orderCount`: 購買數量
- `account`: 扣款帳號

### 2. 查詢喜好金融商品清單
**GET** `/api/likes`

**回應範例：**
```json
[
  {
    "productName": "基金A",
    "account": "12345678",
    "totalAmount": 5000.00,
    "totalFee": 50.00,
    "email": "test@email.com"
  }
]
```

**說明：**
- `productName`: 產品名稱
- `account`: 扣款帳號
- `totalAmount`: 預計扣款總金額
- `totalFee`: 總手續費用
- `email`: 使用者聯絡電子信箱

### 3. 刪除喜好金融商品資訊
**DELETE** `/api/like/{sn}`

**請求範例：**
```
DELETE /api/like/1
```

**說明：**
- `{sn}`: 喜好商品記錄的序號

### 4. 更改喜好金融商品資訊
**PUT** `/api/like/{sn}`

**請求範例：**
```json
PUT /api/like/1
{
  "userId": "A123456789",
  "productNo": "B001",
  "orderCount": 3,
  "account": "87654321"
}
```

**說明：**
- `{sn}`: 喜好商品記錄的序號
- 請求體包含要更新的資訊

## 測試資料

### 可用產品
- `A001`: 基金A (價格: 1000.00, 手續費率: 1%)
- `B001`: 保單B (價格: 5000.00, 手續費率: 0.5%)

### 可用使用者
- `A123456789`: 王小明 (email: test@email.com)

## 錯誤處理

- **400 Bad Request**: 請求格式錯誤
- **404 Not Found**: 記錄或產品不存在
- **500 Internal Server Error**: 伺服器內部錯誤

## 使用範例

### 1. 新增喜好商品
```bash
curl -X POST http://localhost:8080/api/like \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "A123456789",
    "productNo": "A001",
    "orderCount": 5,
    "account": "12345678"
  }'
```

### 2. 查詢喜好清單
```bash
curl -X GET http://localhost:8080/api/likes
```

### 3. 刪除喜好商品
```bash
curl -X DELETE http://localhost:8080/api/like/1
```

### 4. 更新喜好商品
```bash
curl -X PUT http://localhost:8080/api/like/1 \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "A123456789",
    "productNo": "B001",
    "orderCount": 3,
    "account": "87654321"
  }'
``` 