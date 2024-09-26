import LoginForm from "@/src/components/login-form";
import { Navbar } from "@/src/components/navbar";

export default async function LoginPage() {
  return (
    <main >
      <div>
        <Navbar/>
        <LoginForm />
      </div>
    </main>
  );
}
