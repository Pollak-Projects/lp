import LoginForm from "@/src/components/login-form";
import { NavbarCustom } from "@/src/components/NavbarCustom";
import { auth } from "@/src/auth";

export default async function LoginPage() {
  const session = (await auth()) ?? false;

  let loggedIn = !session;

  return (
    <main>
      <section className={"pt-2"}>
        <div>
          <NavbarCustom/>
          <LoginForm />
        </div>
      </section>
    </main>
  );
}
