import { useQueries, useQuery } from "@tanstack/react-query";
import {
  createFullQuizAnswer,
  getAllQuizAnswers,
  getAllQuizAnswersByUserId,
  getQuizAnswerById,
  getQuizByQuizAnswerId
} from "@/src/app/actions/QuizAnswerActions";
import { QuizAnswerData } from "@/src/types/questionAnswer/quizAnswerData";

export function useGetAllQuizAnswersByUserId() {
  return useQuery({
    queryFn: async () => getAllQuizAnswersByUserId(),
    queryKey: ["getAllQuizAnswersByUserId"],
  });
}

export function useGetAllQuizAnswers() {
  return useQuery({
    queryFn: async () => getAllQuizAnswers(),
    queryKey: ["getAllQuizAnswers"],
  });
}

export function useGetQuizAnswerById(quizAnswerId: string) {
  return useQuery({
    queryFn: async () => getQuizAnswerById(quizAnswerId),
    queryKey: ["getQuizAnswerById", quizAnswerId],
  });
}

export function useGetQuizzesByQuizAnswerIds(quizAnswerIds: string[]) {
  return useQueries({
    queries: quizAnswerIds.map((quizAnswerId) => ({
      queryFn: async () => getQuizByQuizAnswerId(quizAnswerId),
      queryKey: ["getQuizAnswerById", quizAnswerId],
    })),
  });
}


export function useCreateFUllQuizAnswer({
  quizAnswerData,
  enabled,
}: {
  quizAnswerData: QuizAnswerData;
  enabled: boolean;
}) {
  return useQuery({
    queryFn: async () => createFullQuizAnswer(quizAnswerData),
    queryKey: ["createFullQuizAnswer"],
    enabled,
  });
}