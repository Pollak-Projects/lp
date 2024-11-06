import { useState } from "react";

export default function QuizSidebar({dummyDataTitles}:{dummyDataTitles: () => string[]}) {

  const [titles, setTitles] = useState(dummyDataTitles);

  function printTitles() {
    return (
      //how the fucking hell can we do dynamic bookmarks??? like, for real?
      <>

      </>
    )
  }

  return (
    <>

    </>
  )
}