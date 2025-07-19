<template>
  <form @submit.prevent="onSubmit">
    <div class="form-row">
      <label>使用者ID</label>
      <select v-model="form.userId" required>
        <option value="" disabled>請選擇</option>
        <option v-for="user in users" :key="user.userId" :value="user.userId">
          {{ user.userId }}（{{ user.userName }}）
        </option>
      </select>
    </div>
    <div class="form-row">
      <label>產品編號</label>
      <select v-model="form.productNo" required>
        <option value="" disabled>請選擇</option>
        <option v-for="product in products" :key="product.no" :value="product.no">
          {{ product.no }}（{{ product.productName }}）
        </option>
      </select>
    </div>
    <div class="form-row">
      <label>購買數量</label>
      <input type="number" v-model.number="form.orderCount" required min="1" />
    </div>
    <div class="form-row">
      <label>扣款帳號</label>
      <select v-model="form.account" required>
        <option value="" disabled>請選擇</option>
        <option v-for="acc in accounts" :key="acc" :value="acc">
          {{ acc }}
        </option>
      </select>
    </div>
    <div class="form-row">
      <button type="submit">{{ form.sn ? '更新' : '新增' }}</button>
      <button v-if="form.sn" type="button" @click="clearEdit">取消編輯</button>
    </div>
  </form>
</template>

<script setup>
import { ref, watch, onMounted, defineEmits, defineProps } from 'vue'
import api from '../api'

const props = defineProps(['editLike'])
const emit = defineEmits(['refresh', 'clear-edit'])

const form = ref({
  userId: '',
  productNo: '',
  orderCount: 1,
  account: '',
  sn: null
})

const users = ref([])
const products = ref([])
const accounts = ref([])

onMounted(async () => {
  // 取得所有使用者
  const userRes = await api.getUsers()
  users.value = userRes.data
  // 取得所有產品
  const productRes = await api.getProducts()
  products.value = productRes.data
  // 取得所有帳號（去重）
  accounts.value = [...new Set(userRes.data.map(u => u.account))]
})

watch(() => props.editLike, (val) => {
  if (val) {
    form.value = { ...val }
  } else {
    form.value = { userId: '', productNo: '', orderCount: 1, account: '', sn: null }
  }
})

const onSubmit = async () => {
  if (form.value.sn) {
    // 更新
    await api.updateLike(form.value.sn, {
      userId: form.value.userId,
      productNo: form.value.productNo,
      orderCount: form.value.orderCount,
      account: form.value.account
    })
    emit('clear-edit')
  } else {
    // 新增
    await api.addLike({
      userId: form.value.userId,
      productNo: form.value.productNo,
      orderCount: form.value.orderCount,
      account: form.value.account
    })
  }
  emit('refresh')
  form.value = { userId: '', productNo: '', orderCount: 1, account: '', sn: null }
}

const clearEdit = () => {
  emit('clear-edit')
  form.value = { userId: '', productNo: '', orderCount: 1, account: '', sn: null }
}
</script>

<style scoped>
form {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
  margin-bottom: 24px;
  align-items: flex-end;
}
.form-row {
  display: flex;
  flex-direction: column;
  margin-right: 16px;
}
label {
  margin-bottom: 4px;
  font-weight: bold;
}
input, select {
  padding: 4px 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
button {
  margin-top: 8px;
  padding: 6px 18px;
  border: none;
  background: #67c23a;
  color: #fff;
  border-radius: 4px;
  cursor: pointer;
}
button[type="button"] {
  background: #909399;
}
</style> 