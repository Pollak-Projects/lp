"use client";
import { QuizData } from "@/src/types/question/quizData";
import { Button } from "@nextui-org/button";
import { PressEvent } from "@react-types/shared";
import { useCreateFullQuiz } from "@/src/data/crudQuiz";


export default function QuizCreateButton({ quizData }: { quizData: QuizData }) {
  const { data, error, fetchStatus, refetch } = useCreateFullQuiz({ quizData, enabled: false });

  const handleQuizCreate = (e: PressEvent) => {
    refetch();
  };

  return (
    <>
      <Button onPress={handleQuizCreate}>Create Quiz</Button>
    </>
  );
}
