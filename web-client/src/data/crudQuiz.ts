import { useQuery } from "@tanstack/react-query";

import { QuizData } from "@/src/types/question/quizData";
import {
  createFullQuiz,
  getAllQuizzes,
  getAllQuizzesByUser,
  getQuizById
} from "@/src/app/actions/QuizActions";

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

export function useGetAllQuizzesById(userId: string) {
  return useQuery({
    queryFn: async () => getAllQuizzesByUser(userId),
    queryKey: ["getAllQuizzesById", userId],
  });
}

export function useGetQuizById(quizId: string) {
  return useQuery({
    queryFn: async () => getQuizById(quizId),
    queryKey: ["getQuizById", quizId],
  });
}
