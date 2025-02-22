import { MainLayout } from '@/app/components/layout/MainLayout';
import { UserForm } from '@/app/components/form/UserForm';
import { useSectors } from '@/app/hooks/useSectors';
import { useRouter } from 'next/router';
import { UserFormData } from "@/app/types/types";

export default function HomePage() {
  const { sectors, loading, error } = useSectors();
  const router = useRouter();

  const handleSubmit = async (data: UserFormData) => {
    try {
      const response = await fetch('/users/save', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
      });

      const result = await response.json();
      await router.push(`/edit/${result.editToken}`);
    } catch {
      alert('Submission failed');
    }
  };

  if (loading) return <MainLayout>Loading sectors...</MainLayout>;
  if (error) return <MainLayout>{error}</MainLayout>;

  return (
    <MainLayout>
      <h1 className="text-2xl font-bold mb-6">Sector Selection Form</h1>
      <UserForm sectors={sectors} onSubmit={handleSubmit} />
    </MainLayout>
  );
}