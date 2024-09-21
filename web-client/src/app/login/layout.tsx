export default function LoginLayout(
  {
    children,
  }: {
    children: React.ReactNode
  }) {
  return (
    <main>
      <section>
        <div>{children}</div>
      </section>
    </main>
  )
}