import { auth } from "@/src/auth";
import { jwtDecode } from "jwt-decode";

// THIS IS NOT NEEDED
export async function GET() {
  const session = await auth();
  console.log("random stuff");
  console.log(session);
  const urla = `${process.env.KEYCLOAK_SESSION_END_URL}?id_token_hint=${encodeURIComponent(session?.token?.id_token || "")}&post_logout_redirect_uri=${encodeURIComponent(process.env.HOST_URL || "")}`; //?id_token_hint=${idToken}&post_logout_redirect_uri=${process.env.HOST_URL}

  if (session) {
    // const idToken = await getIdToken();
    const urla = `${process.env.KEYCLOAK_SESSION_END_URL}?id_token_hint=${encodeURIComponent(session?.token?.id_token || "")}&post_logout_redirect_uri=${encodeURIComponent(process.env.HOST_URL || "")}`; //?id_token_hint=${idToken}&post_logout_redirect_uri=${process.env.HOST_URL}

    console.log(urla);

    try {
      const resp = fetch(urla, { method: "POST" });
    } catch (error) {
      console.error("Failed to logout:", error);
      return new Response("Failed to logout", { status: 500 });
    }
  }
  return new Response("Logged out" + urla, { status: 200 });
}
