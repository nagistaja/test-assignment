'use client';
import Link from 'next/link';
import { useState } from 'react';

export default function HomePage() {
  const [token, setToken] = useState('');

  const handleEdit = () => {
    if (!token) return;
    window.location.href = `/edit/${token}`;
  };

  return (
    <div className="max-w-2xl mx-auto p-4">
      <h1 className="text-2xl font-bold mb-6">Welcome to Sector Selection</h1>

      <div className="mb-8">
        <Link href="/new">
          <button className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
            Create a New Submission
          </button>
        </Link>
      </div>

      <div className="mb-4">
        <label className="block font-medium mb-2">Edit an Existing Submission</label>
        <input
          type="text"
          value={token}
          onChange={(e) => setToken(e.target.value)}
          placeholder="Enter your edit token"
          className="border p-2 w-full mb-2"
        />
        <button
          onClick={handleEdit}
          className="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600"
        >
          Edit
        </button>
      </div>
    </div>
  );
}
