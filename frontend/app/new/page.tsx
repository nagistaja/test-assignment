'use client';
import { useState } from 'react';
import { MainLayout } from '@/app/components/layout/MainLayout';
import { useSectors } from '@/app/hooks/useSectors';
import { UserForm } from '@/app/components/form/UserForm';
import { UserFormData } from '@/app/types/types';

export default function NewPage() {
  const { sectors, loading, error } = useSectors();
  const [savedToken, setSavedToken] = useState<string | null>(null);

  const handleSubmit = async (data: UserFormData) => {
    try {
      const response = await fetch(process.env.NEXT_PUBLIC_API_URL + '/users/save', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data),
      });
      const result = await response.json();
      if (!response.ok) throw new Error(result.error || 'Submission failed');
      setSavedToken(result.editToken);
    } catch (err: any) {
      alert(err.message);
    }
  };

  return (
    <MainLayout>
      <h1 className="text-2xl font-bold mb-6">Create a New Submission</h1>
      {loading && <p>Loading sectors...</p>}
      {error && <p className="text-red-500">{error}</p>}

      {!loading && sectors.length > 0 && !savedToken && (
        <UserForm
          sectors={sectors}
          onSubmit={handleSubmit}
        />
      )}
      {savedToken && (
        <div className="p-4 border rounded mt-4 bg-green-50">
          <h2 className="font-bold">Submission Saved!</h2>
          <p>Your edit token is: <span className="font-mono">{savedToken}</span></p>
          <p>Keep this token safe to edit your submission later.</p>
          <button
            onClick={() => window.location.href = `/edit/${savedToken}`}
            className="bg-blue-500 text-white px-4 py-2 rounded mt-2 hover:bg-blue-600"
          >
            Edit Now
          </button>
        </div>
      )}
    </MainLayout>
  );
}
