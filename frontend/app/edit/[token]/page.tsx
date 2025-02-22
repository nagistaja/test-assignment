'use client';
import { useParams } from 'next/navigation';
import { useEffect, useState } from 'react';
import { MainLayout } from '@/app/components/layout/MainLayout';
import { UserForm } from '@/app/components/form/UserForm';
import { useSectors } from '@/app/hooks/useSectors';
import { UserFormData } from '@/app/types/types';

export default function EditPage() {
  const params = useParams();
  const token = params.token as string | undefined;
  const { sectors, loading, error } = useSectors();
  const [initialData, setInitialData] = useState<UserFormData | null>(null);
  const [fetchError, setFetchError] = useState<string | null>(null);

  useEffect(() => {
    if (token) {
      fetch(`${process.env.NEXT_PUBLIC_API_URL}/users/edit/${token}`)
        .then((res) => {
          if (!res.ok) throw new Error('User not found or token invalid');
          return res.json();
        })
        .then((data: UserFormData) => setInitialData(data))
        .catch((err) => setFetchError(err.message));
    }
  }, [token]);

  const handleSubmit = async (data: UserFormData) => {
    try {
      const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/users/update/${token}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data),
      });
      if (!response.ok) throw new Error('Update failed');
      alert('Changes saved successfully!');
    } catch {
      alert('Update failed');
    }
  };

  if (loading) return <MainLayout>Loading sectors...</MainLayout>;
  if (error) return <MainLayout>{error}</MainLayout>;
  if (fetchError)
    return (
      <MainLayout>
        <p className="text-red-500">{fetchError}</p>
      </MainLayout>
    );

  return (
    <MainLayout>
      <h1 className="text-2xl font-bold mb-6">Edit Your Submission</h1>
      {initialData ? (
        <UserForm sectors={sectors} initialData={initialData} onSubmit={handleSubmit} />
      ) : (
        <p>User data not found. Please check your token or try again.</p>
      )}
    </MainLayout>
  );
}