"use server";
import logger from "@logger";

import axiosFetch from "@/src/lib/AxiosFetch";
import { QuizAnswerData } from "@/src/types/questionAnswer/quizAnswerData";

const log = logger("quiz:QuizAnswerActions");

export async function getAllQuizAnswers() {
  const response = await (await axiosFetch())?.get("/api/v1/quiz/answer/all")!;

  if (response.status !== 200) {
    log.error("getAllQuizAnswers", await response.data);
    throw new Error("Failed to get fetch quiz answers");
  }

  log.debug("getAllQuizAnswers", response.data);

  return response.data;
}

export async function getQuizByQuizAnswerId(quizAnswerId: string) {
  const response = await (
    await axiosFetch()
  )?.get(`/api/v1/quiz?quizAnswerId=${quizAnswerId}`)!;

  if (![200, 204].includes(response.status)) {
    log.error("getQuizByQuizAnswerId", response.data);
    throw new Error("Failed to get fetch quiz by quiz answer id");
  }

  log.debug("getQuizByQuizAnswerId", response.data);

  return response.data;
}

export async function getQuizzesByQuizAnswerIds(quizAnswerIds: string[]) {
  log.debug("getQuizNamesByQuizAnswerIds", quizAnswerIds);
  const response = await (
    await axiosFetch()
  )?.post("/api/v1/quiz/by-quiz-answer-ids", { quizAnswerIds })!;

  if (![200, 204].includes(response.status)) {
    log.error("getQuizNamesByQuizAnswerIds", response.data);
    throw new Error("Failed to get fetch quiz names by quiz answer ids");
  }

  return response.data;
}

export async function getAllQuizAnswersByUserId() {
  const response = await (await axiosFetch())?.get("/api/v1/quiz/answer/current-user")!

  if (![200, 204].includes(response.status)) {
    log.error("getAllQuizAnswersByUserId", response.data)
    throw new Error("Failed to get fetch quiz answers by user id")
  }

  return response.data
}

export async function getQuizAnswerById(quizId: string) {
  const response = await (
    await axiosFetch()
  )?.get(`/api/v1/quiz/answer?id=${quizId}`)!;

  if (response.status !== 200) {
    log.error("getQuizAnswerById", response.data);
    throw new Error("Failed to get fetch quiz answer");
  }

  log.debug("getQuizAnswerById", response.data);

  return response.data;
}

export async function createFullQuizAnswer(quizAnswerData: QuizAnswerData) {
  const response = await (
    await axiosFetch()
  )?.post("/api/v1/quiz/answer/full", {
    ...quizAnswerData
  })!;

  if (response.status !== 200) {
    log.error("createQuizAnswer", { json: await response.data });
    throw new Error("Failed to create quiz answer");
  }

  log.debug("createQuizAnswer", response.data);

  return response.data;
}
