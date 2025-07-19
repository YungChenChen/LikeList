import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8080/api'
})

export default {
  getLikes: () => api.get('/likes'),
  addLike: (data) => api.post('/like', data),
  updateLike: (sn, data) => api.put(`/like/${sn}`, data),
  deleteLike: (sn) => api.delete(`/like/${sn}`),
  getUsers: () => api.get('/users'),
  getProducts: () => api.get('/products')
} 