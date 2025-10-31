import { useState } from 'react';
import { api } from '../../shared/api/client';

type CreateTaskRequest = { title: string; description?: string; dueDateIso?: string };

export default function TaskForm({ onCreated }: { onCreated: () => void }){
const [title, setTitle] = useState('');
const [desc, setDesc] = useState('');
const submit = async (e: React.FormEvent) => {
e.preventDefault();
await api.post('/tasks', { title, description: desc } as CreateTaskRequest);
setTitle(''); setDesc(''); onCreated();
};
return (
<form onSubmit={submit} className="space-y-2">
<input className="border p-2 w-full" placeholder="Title" value={title} onChange={e=>setTitle(e.target.value)} required />
<textarea className="border p-2 w-full" placeholder="Description" value={desc} onChange={e=>setDesc(e.target.value)} />
<button className="px-4 py-2 rounded bg-black text-white">Create</button>
</form>
);
}