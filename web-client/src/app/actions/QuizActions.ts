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

export async function getAllQuizzesByUser(quizId: string) {
  const response = await (await axiosFetch())?.get(`/api/v1/quiz/allbyuser?id=${quizId}`)!;

  if (response.status !== 200) {
    log.error("getAllQuizzesByUser", { json: await response.data });
    throw new Error("Failed to get fetch quizzesByUser");
  }

  log.debug("getAllQuizzesByUser", response.data);

  return response.data;
}

export async function createFullQuiz(quizData: QuizData) {

  log.debug("createQuizData", quizData);

  const response = await (
    await axiosFetch()
  )?.post("/api/v1/quiz/full", {
    ...quizData
  })!;

  log.debug(`createQuizResponse ${await response.status}`);

  if (![200, 201].includes(response.status)) {
    log.error("createQuiz", await response.data);
    throw new Error("Failed to create quiz");
  }

  log.debug(`createQuiz ${await response.data}`, await response.data);

  return response.data;
}
