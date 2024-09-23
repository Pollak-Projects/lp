import { auth } from "@/src/auth";

export async function GET() {
  const session = await auth()

  if (session) {
    // const idToken = await getIdToken();

    const url = `${process.env.KEYCLOAK_SESSION_END_URL}&post_logout_redirect_uri=${encodeURIComponent(process.env.HOST_URL || "")}&client_id=${process.env.AUTH_KEYCLOAK_ID}`; //?id_token_hint=${idToken}&post_logout_redirect_uri=${process.env.HOST_URL}

    try {
      const resp = fetch(url, {method: "GET"}).then(res => {
        console.log("DSFJJLSGKSKSLKJJGDKL")
      });
    } catch (error) {
      console.error("Failed to logout:", error);
      return new Response("Failed to logtout", { status: 500 });
    }
  }
  return new Response("Logged out", { status: 303 });
}