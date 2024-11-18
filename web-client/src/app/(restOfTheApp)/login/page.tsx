import LoginForm from "@/src/components/login-form";
import { auth } from "@/src/auth";
import { cookies } from "next/headers";

export default async function LoginPage() {
  const session = (await auth()) ?? false;

  let loggedIn = !session;

  const csrfToken = (await cookies()).get("authjs.csrf-token")?.value ?? "";

  return (
    <main>
      <section className={"pt-2"}>
        <div>
          <LoginForm csrfToken={csrfToken} />
        </div>
      </section>
    </main>
  );
}
