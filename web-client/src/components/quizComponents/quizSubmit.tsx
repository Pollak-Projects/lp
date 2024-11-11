import { QuizData } from "@/src/types/question/quizData";
import { Button } from "@nextui-org/button";

export default function QuizSubmit({QuizDummyData}:{QuizDummyData: QuizData}) {

  function submitAllValues():void{

  }

  return (
    <>
      <section className={"flex flex-col place-items-center h-full w-full bg-content2 rounded-lg"}>
        <div className={"h-[8%] border-b border-gray-200 w-full m-2 pb-2 text-center place-content-center"}>
          <span className={"text-2xl"}>Quiz Status</span>
        </div>
        <div className={"h-[37%] border-b flex px-2 flex-row border-gray-200 w-full m-2 pb-2 text-center"}>
          <div className={"text-left"}>
            Deadline:
          </div>
          <div className={"text-right ml-auto"}>
            {QuizDummyData.deadline}

          </div>
        </div>

        <div className={"h-[37%] border-b border-gray-200 w-full m-2 pb-2 text-center"}>

        </div>

        <div className={"h-[10%] justify-self-end border-gray-200 w-full m-2 pb-2 text-center place-content-center"}>
          <Button color={"primary"} onClick={submitAllValues}>Finalize</Button>
        </div>
      </section>
    </>
  )
}