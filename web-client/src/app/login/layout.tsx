export default function LoginLayout(
  {
    children,
  }: {
    children: React.ReactNode
  }) {
  return (
    <main style={{width: "21.5em"}}>
        <div className={"bg-secondary mt-2 rounded-md w-full"}
        style={{borderRadius: "0.6em"}}>
          {children}</div>

    </main>
  )
}