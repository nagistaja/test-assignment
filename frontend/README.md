# Helmes Frontend (Next.js)

## Getting Started

This directory contains the **Next.js** application for the Helmes Fullstack App. It communicates with the Spring Boot backend to display and update user information.

## Requirements

- **Node.js 22+** (if running locally)
- **npm** or **yarn** installed

## Installation

From the `frontend/` directory:

```bash
npm install
```

## Running Locally in Development
```bash
npm run dev
# or
yarn dev
# or
pnpm dev
# or
bun dev
```

Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

## Environment Variables
`NEXT_PUBLIC_API_URL:` The base URL for the backend API. For example:
```bash
NEXT_PUBLIC_API_URL=http://localhost:8080/api
```
Set this in a .env.local file if needed.