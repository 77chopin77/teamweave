import { useEffect, useState } from 'react';
import { api } from '../../shared/api/client';

type Task = { id: string; title: string; description?: string; status: 'TODO'|'DOING'|'DONE' };

export default function TaskList(){
const [tasks, setTasks] = useState<Task[]>([]);
const load = async()=> {
const res = await api.get<Task[]>('/tasks'); // GET 実装後に利用
setTasks(res.data);
};
useEffect(()=>{ load(); },[]);
return (
<div className="grid gap-2">
{tasks.map(t=> (
<div key={t.id} className="border rounded p-3">
<div className="font-medium">{t.title}</div>
<div className="text-sm opacity-70">{t.description}</div>
</div>
))}
</div>
);
}