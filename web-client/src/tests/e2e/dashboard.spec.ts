import { expect, test } from "@playwright/test";

test("quiz renders", async ({ page }) => {
  await page.goto("http://localhost:3000/");

  await page.getByRole("link", { name: "Dashboard" }).click();

  // Expect a title "to contain" a substring.
  await expect(page).toHaveURL("http://localhost:3000/dashboard");

  await expect(page.locator("h1")).toHaveText("Hello, Next.js!");

  await expect(page.locator("div").first()).toHaveText("Dummy Component");
});
