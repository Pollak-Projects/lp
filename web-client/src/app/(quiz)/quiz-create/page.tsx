import React from "react";
import { NavbarCustom } from "@/src/components/NavbarCustom";
import QuizCreatePage from "@/src/components/QuizCreateComponents/QuizCreatePage";

export default function QuizCreate() {

  return (
    <>
      <NavbarCustom/>
      <div className={"inline-flex h-full w-full"}>
        <QuizCreatePage />
      </div>
    </>
  );
}