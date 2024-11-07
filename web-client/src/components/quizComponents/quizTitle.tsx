import { QuizData } from "@/src/types/quizData";

export default function QuizTitle({QuizDummyData}:{QuizDummyData: QuizData}) {
  return (
    <>
      <section className={"flex flex-row gap-4 text-center justify-center w-full bg-content2"}>
        <span className={"text-2xl"}>
          {QuizDummyData.title}
        </span>
      </section>
    </>
  )
}