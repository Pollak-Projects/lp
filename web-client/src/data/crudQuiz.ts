import { useQuery } from "@tanstack/react-query";

import { QuizData } from "@/src/types/question/quizData";
import { createFullQuiz, getAllQuizzes, getAllQuizzesByUser, getQuiz } from "@/src/app/actions/QuizActions";

export function useCreateFullQuiz({
  quizData,
  enabled,
}: {
  quizData: QuizData;
  enabled: boolean;
}) {
  return useQuery({
    queryFn: async () => createFullQuiz(quizData),
    queryKey: ["createFullQuiz"],
    enabled,
  });
}

export function useGetAllQuizzes() {
  return useQuery({
    queryFn: async () => getAllQuizzes(),
    queryKey: ["getAllQuizzes"],
  });
}

export function useGetAllQuizzesById(quizId: string) {
  return useQuery({
    queryFn: async () => getAllQuizzesByUser(quizId),
    queryKey: ["getAllQuizzesById", quizId],
  });
}

export function useGetQuizById(quizId: string) {
  return useQuery({
    queryFn: async () => getQuiz(quizId),
    queryKey: ["getAllQuizzesById", quizId],
  });
}
