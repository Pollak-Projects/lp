import LoginForm from "@/src/components/login-form";
import { Navbar } from "@/src/components/navbar";
import { auth } from "@/src/auth";

export default async function LoginPage() {
  const session = (await auth()) ?? false;

  let loggedIn = !session;

  return (
    <main>
      <section className={"pt-2"}>
        <div>
          <Navbar loggedIn={!loggedIn} />
          <LoginForm />
        </div>
      </section>
    </main>
  );
}
