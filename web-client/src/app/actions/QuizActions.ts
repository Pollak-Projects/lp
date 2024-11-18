"use server";

import logger from "@logger";

import axiosFetch from "@/src/lib/AxiosFetch";
import { QuizData } from "@/src/types/question/quizData";

const log = logger("quiz:QuizActions");

export async function getAllQuizzes() {
  const response = await (await axiosFetch())?.get("/api/v1/quiz/all")!;

  if (response.status !== 200) {
    log.error("getAllQuizzes", { json: await response.data });
    throw new Error("Failed to get fetch quizzes");
  }

  log.debug("getAllQuizzes", response.data);

  return response.data;
}

export async function getQuiz(quizId: string) {
  const response = await (await axiosFetch())?.get(`/api/v1/quiz/?id=${quizId}`)!;

  if (response.status !== 200) {
    log.error("getQuiz", { json: await response.data });
    throw new Error("Failed to get fetch quiz");
  }

  log.debug("getQuiz", response.data);

  return response.data;
}

export async function createFullQuiz(quizData: QuizData) {
  const response = await (
    await axiosFetch()
  )?.post("/api/v1/quiz/full", {
    ...quizData
  })!;

  if (response.status !== 200) {
    log.error("createQuiz", { json: await response.data });
    throw new Error("Failed to create quiz");
  }

  log.debug("createQuiz", response.data);

  return response.data;
}
