import { NavbarCustom } from "@/src/components/NavbarCustom";
import { Metadata } from "next";

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return <>{children}</>;
}

export const metadata: Metadata = {
  title: 'Quiz Selection',
}