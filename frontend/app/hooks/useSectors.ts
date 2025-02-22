'use client';
import { useEffect, useState } from 'react';
import { Sector } from '@/app/types/types';

export const useSectors = () => {
  const [sectors, setSectors] = useState<Sector[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchSectors = async () => {
      try {
        const res = await fetch(process.env.NEXT_PUBLIC_API_URL + '/sectors/hierarchical');
        if (!res.ok) throw new Error('Failed to fetch sectors');
        const data = await res.json();
        setSectors(data);
      } catch (err: any) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };
    fetchSectors();
  }, []);
  return { sectors, loading, error };
};
