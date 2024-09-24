export default function LoginLayout(
  {
    children,
  }: {
    children: React.ReactNode
  }) {
  return (
    <main>
      <section>
        <div className={"bg-secondary mt-2 rounded-md"}
        style={{borderRadius: "0.6em"}}>
          {children}</div>
      </section>
    </main>
  )
}