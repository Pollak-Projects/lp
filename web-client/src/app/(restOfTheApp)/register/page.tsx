import { NavbarCustom } from "@/src/components/NavbarCustom";
import { auth } from "@/src/auth";
import RegisterForm from "@/src/components/register-form";

export default async function RegisterPage() {

  return (
    <main>
      <div>
        <NavbarCustom />
        <RegisterForm />
      </div>
    </main>
  );
}
