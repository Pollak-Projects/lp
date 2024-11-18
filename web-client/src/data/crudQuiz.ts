import { QuizData } from "@/src/types/question/quizData";
import { useQuery } from "@tanstack/react-query";
import { createFullQuiz } from "@/src/app/actions/QuizActions";

export function useCreateFullQuiz({ quizData, enabled }: { quizData: QuizData, enabled: boolean }) {
  return useQuery({
    queryFn: async () => createFullQuiz(quizData),
    queryKey: ["createFullQuiz"],
    enabled
  });
}