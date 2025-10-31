import TaskForm from '../features/tasks/TaskForm';
import TaskList from '../features/tasks/TaskList';
import { useState } from 'react';

export default function App(){
const [refreshKey, setRefreshKey] = useState(0);
return (
<div className="max-w-3xl mx-auto p-6 space-y-6">
<h1 className="text-2xl font-bold">TeamWeave</h1>
<TaskForm onCreated={()=>setRefreshKey(k=>k+1)} />
{/* TaskList は refreshKey を依存にして再読み込みしてもOK */}
<TaskList />
</div>
);
}