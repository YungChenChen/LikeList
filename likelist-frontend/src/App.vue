<template>
  <div class="container">
    <h1>喜好金融商品管理</h1>
    <like-form :edit-like="editLike" @refresh="fetchLikes" @clear-edit="editLike = null"/>
    <like-list-table
      :likes="likes"
      @edit="onEdit"
      @delete="onDelete"
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import api from './api'
import LikeListTable from './components/LikeListTable.vue'
import LikeForm from './components/LikeForm.vue'

const likes = ref([])
const editLike = ref(null)

const fetchLikes = async () => {
  const res = await api.getLikes()
  likes.value = res.data
}

const onEdit = (like, sn) => {
  editLike.value = { ...like, sn }
}

const onDelete = async (sn) => {
  if (confirm('確定要刪除這筆資料嗎？')) {
    await api.deleteLike(sn)
    fetchLikes()
  }
}

onMounted(fetchLikes)
</script>

<style>
.container {
  max-width: 800px;
  margin: 40px auto;
  padding: 24px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px #eee;
}
h1 {
  text-align: center;
  margin-bottom: 24px;
}
</style> 