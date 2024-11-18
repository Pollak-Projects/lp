"use client";
import { QuizData } from "@/src/types/question/quizData";
import { Button } from "@nextui-org/button";
import { PressEvent } from "@react-types/shared";
import { useCreateFullQuiz } from "@/src/data/crudQuiz";


export default function QuizCreateButton({ quizData }: { quizData: QuizData }) {
  const query = useCreateFullQuiz({ quizData, enabled: false });

  const handleQuizCreate = (e: PressEvent) => {
    query.refetch();
  };

  return (
    <>
      <Button onPress={handleQuizCreate}>Create Quiz</Button>
      {query.isLoading ? (
        <p>Loading...</p>
      ) : query.isError ? (
        <p>Error: {query.error.message}</p>
      ) : query.isSuccess ? (
        <p>{JSON.stringify(query.data, null, 2)}</p>
      ) : (
        <p>Click the button to create a quiz</p>
      )}
    </>
  );
}
