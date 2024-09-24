import LoginForm from "@/src/components/login-form";
import { nextAuthConfig } from "@/src/lib/next-auth-options";

export default async function LoginPage() {
  return (
    <main className="flex items-center justify-center md:h-screen">
      <div className=" mx-auto flex w-full max-w-[500px] flex-col space-y-2.5 p-4 md:-mt-32">
        <LoginForm />
      </div>
    </main>
  );
}
