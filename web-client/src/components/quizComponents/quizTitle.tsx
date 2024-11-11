import { QuizData } from "@/src/types/question/quizData";

export default function QuizTitle({QuizDummyData}:{QuizDummyData: QuizData}) {
  return (
    <>
      <section className={"flex flex-col p-3 gap-4 text-center rounded-lg justify-center w-full bg-content2"}>
        <span className={"text-2xl"}>
          {QuizDummyData.title}
        </span>
        <div className={"text-center"}>
          Assigned by: <span className={"italic"}>{QuizDummyData.assigner}</span>
        </div>
        <span className={"text-center"}>
          {QuizDummyData.description}
        </span>
      </section>
    </>
  )
}