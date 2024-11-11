import { NavbarCustom } from "@/src/components/NavbarCustom";
import { auth } from "@/src/auth";
import RegisterForm from "@/src/components/register-form";

export default async function RegisterPage() {
  const session = (await auth()) ?? false;

  let loggedIn = !session;

  return (
    <main>
      <div>
        <NavbarCustom loggedIn={!loggedIn} />
        <RegisterForm />
      </div>
    </main>
  );
}
