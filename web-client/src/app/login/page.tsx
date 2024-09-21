import LoginForm from '@/src/app/ui/login-form';
import getServerSession from "next-auth";
import { SignIn, SignOut } from "@/src/components/next-auth-buttons";
import { nextAuthConfig } from "@/src/lib/next-auth-options";


export default async function LoginPage() {
  const session = await getServerSession(nextAuthConfig);
  return (
    <main className="flex items-center justify-center md:h-screen">
      <div className="relative mx-auto flex w-full max-w-[400px] flex-col space-y-2.5 p-4 md:-mt-32">
        <main>
          {!!session && <pre>{JSON.stringify(session, null, 2)}</pre>}
          <SignOut />
          <SignIn />
        </main>
      </div>
    </main>
  );
}