"use client";

import { Button } from "@nextui-org/button";
import NextLink from "next/link";
import clsx from "clsx";
import { link } from "@nextui-org/theme";
import React from "react";

import { QuizAnswerData } from "@/src/types/questionAnswer/quizAnswerData";
import { useGetQuizzesByQuizAnswerIds } from "@/src/data/crudQuizAnswer";

export default function QuizAnswerSelectPage({
  quizAnswers,
}: {
  quizAnswers: QuizAnswerData[];
}) {
  const quizGradePath = "/quiz-grade/";

  const responses = useGetQuizzesByQuizAnswerIds(
    quizAnswers.map((qa) => qa.id)
  );

  return (
    <>
      {responses.map((response, index) => (
        <div key={index}>
          {response.isLoading ? (
            <p>Loading...</p>
          ) : response.isError ? (
            <p>Error: {response.error.message}</p>
          ) : response.isSuccess ? (
            <div>
              <Button>
                <NextLink
                  className={clsx(
                    link({ color: "foreground" }),
                    "data-[active=true]:text-primary data-[active=true]:font-medium"
                  )}
                  color={"foreground"}
                  href={quizGradePath + quizAnswers[index].id}
                >
                  {response.data.name} - {quizAnswers[index].createdBy}
                </NextLink>
              </Button>
            </div>
          ) : (
            <></>
          )}
        </div>
      ))}
    </>
  );
}
