import './styles/globals.css';
import { ReactNode } from 'react';
import { ErrorBoundary } from '@/app/components/ErrorBoundary';

export const metadata = {
  title: 'Sector Selection App',
  description: 'Select sectors and manage your preferences',
};

export default function RootLayout({ children }: { children: ReactNode }) {
  return (
    <html lang="en">
      <body>
        <ErrorBoundary>
          {children}
        </ErrorBoundary>
      </body>
    </html>
  );
}