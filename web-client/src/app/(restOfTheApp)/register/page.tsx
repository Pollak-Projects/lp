import { Navbar } from "@/src/components/navbar";
import { auth } from "@/src/auth";
import RegisterForm from "@/src/components/register-form";

export default async function RegisterPage() {
  const session = (await auth()) ?? false;

  let loggedIn = !session;

  return (
    <main>
      <div>
        <Navbar loggedIn={!loggedIn} />
        <RegisterForm />
      </div>
    </main>
  );
}
