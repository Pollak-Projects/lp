"use server";
import logger from "@logger";

import axiosFetch from "@/src/lib/AxiosFetch";
import { QuizAnswerData } from "@/src/types/questionAnswer/quizAnswerData";

const log = logger("quiz:QuizAnswerActions");

export async function getAllQuizAnswers() {
  const response = await (await axiosFetch())?.get("/api/v1/quiz/answers/all")!;

  if (response.status !== 200) {
    log.error("getAllQuizAnswers", { json: await response.data });
    throw new Error("Failed to get fetch quiz answers");
  }

  log.debug("getAllQuizAnswers", response.data);

  return response.data;
}

export async function getQuizAnswerById(quizId: string) {
  const response = await (
    await axiosFetch()
  )?.get(`/api/v1/quiz/answers/?id=${quizId}`)!;

  if (response.status !== 200) {
    log.error("getQuizAnswerById", { json: await response.data });
    throw new Error("Failed to get fetch quiz answer");
  }

  log.debug("getQuizAnswerById", response.data);

  return response.data;
}

export async function createFullQuizAnswer(quizAnswerData: QuizAnswerData) {
  const response = await (
    await axiosFetch()
  )?.post("/api/v1/quiz/answers/full", {
    ...quizAnswerData
  })!;

  if (response.status !== 200) {
    log.error("createQuizAnswer", { json: await response.data });
    throw new Error("Failed to create quiz answer");
  }

  log.debug("createQuizAnswer", response.data);

  return response.data;
}
