<template>
  <table>
    <thead>
      <tr>
        <th>產品名稱</th>
        <th>帳號</th>
        <th>總金額</th>
        <th>手續費</th>
        <th>電子信箱</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(like, idx) in likes" :key="idx">
        <td>{{ like.productName }}</td>
        <td>{{ like.account }}</td>
        <td>{{ like.totalAmount }}</td>
        <td>{{ like.totalFee }}</td>
        <td>{{ like.email }}</td>
        <td>
          <button @click="$emit('edit', like, like.sn)">編輯</button>
          <button @click="$emit('delete', like.sn)">刪除</button>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script setup>
defineProps(['likes'])
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 24px;
}
th, td {
  border: 1px solid #ddd;
  padding: 8px 12px;
  text-align: center;
}
th {
  background: #f5f5f5;
}
button {
  margin: 0 4px;
  padding: 4px 12px;
  border: none;
  background: #409eff;
  color: #fff;
  border-radius: 4px;
  cursor: pointer;
}
button:last-child {
  background: #f56c6c;
}
</style> 