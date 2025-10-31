import axios from 'axios';
export const api = axios.create({ baseURL: '/api', withCredentials: false });
export const setAuthToken = (t?: string) => {
if (t) api.defaults.headers.common['Authorization'] = `Bearer ${t}`;
else delete api.defaults.headers.common['Authorization'];
};