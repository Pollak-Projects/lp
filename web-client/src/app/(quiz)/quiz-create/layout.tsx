import { Metadata } from "next";

export default function CreateQuizLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return <>{children}</>;
}

export const metadata: Metadata = {
  title: 'Create Quiz',
}