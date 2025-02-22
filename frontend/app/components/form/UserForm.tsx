'use client';
import React, { useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { SectorSelect } from './SectorSelect';
import { Sector, UserFormData } from '@/app/types/types';

interface UserFormProps {
  sectors: Sector[];
  initialData?: UserFormData;
  onSubmit: (data: UserFormData) => Promise<void>;
}

export const UserForm = ({sectors, initialData, onSubmit,}: UserFormProps) => {
  const {
    register,
    handleSubmit,
    formState: { errors, isSubmitting },
    setValue,
    watch,
    reset,
  } = useForm<UserFormData>({
    defaultValues: initialData,
  });

  useEffect(() => {
    if (initialData) {
      reset(initialData);
    }
  }, [initialData, reset]);

  const nameValidation = register('name', { required: 'Name is required' });
  const sectorIdsValidation = register('sectorIds', {
    validate: (value: number[]) => (value && value.length > 0) || 'Select at least one sector',
  });
  const agreeToTermsValidation = register('agreeToTerms', { required: 'You must agree to terms' });

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="max-w-2xl mx-auto p-4">
      <div className="mb-4">
        <label className="block mb-2 font-medium">Name</label>
        <input
          type="text"
          {...nameValidation}
          className="w-full p-2 border rounded"
        />
        {errors.name && (
          <p className="text-red-500 text-sm">{errors.name.message}</p>
        )}
      </div>
      <div className="mb-4">
        <label className="block mb-2 font-medium">Sectors</label>
        {/* The SectorSelect does not need to register since we call setValue on change */}
        <SectorSelect
          sectors={sectors}
          {...sectorIdsValidation}
          selected={watch('sectorIds') || []}
          onChange={(ids) => setValue('sectorIds', ids, { shouldValidate: true })}
        />
        {errors.sectorIds && (
          <p className="text-red-500 text-sm">{errors.sectorIds.message}</p>
        )}
      </div>
      <div className="mb-4">
        <label className="flex items-center gap-2">
          <input
            type="checkbox"
            {...agreeToTermsValidation}
            className="w-4 h-4"
          />
          Agree to terms
        </label>
        {errors.agreeToTerms && (
          <p className="text-red-500 text-sm">{errors.agreeToTerms.message}</p>
        )}
      </div>
      <div className="flex justify-between items-center">
        <button
          type="submit"
          disabled={isSubmitting}
          className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 disabled:opacity-50"
        >
          {isSubmitting ? 'Saving...' : 'Save'}
        </button>
        <button
          type="button"
          onClick={() => (window.location.href = '/')}
          className="text-blue-500 hover:underline"
        >
          Home
        </button>
      </div>
    </form>
  );
};
