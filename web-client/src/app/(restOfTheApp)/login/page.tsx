import LoginForm from "@/src/components/login-form";
import { Navbar } from "@/src/components/navbar";
import { auth } from "@/src/auth";

export default async function LoginPage() {
  const session = (await auth()) ?? false;

  let loggedIn = !session;

  return (
    <main>
      <div>
        <Navbar loggedIn={!loggedIn} />
        <LoginForm />
      </div>
    </main>
  );
}
