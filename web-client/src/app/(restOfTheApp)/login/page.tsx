import LoginForm from "@/src/components/login-form";
import { nextAuthConfig } from "@/src/lib/next-auth-options";

export default async function LoginPage() {
  return (
    <main className="flex items-center justify-center">
      <div className="  p-4   ">
        <LoginForm />
      </div>
    </main>
  );
}
