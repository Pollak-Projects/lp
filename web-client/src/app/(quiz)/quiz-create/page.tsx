
import QuizCreateSidebar from "@/src/components/QuizCreateComponents/QuizCreateSidebar";
import React from "react";
import { NavbarCustom } from "@/src/components/NavbarCustom";
import { Metadata } from "next";

export default function QuizCreate() {
  return (
    <>
      <NavbarCustom/>
      <div className={"inline-flex h-full w-full"}>
        <div className={"flex-grow"}>Content goes here...</div>
        <div className={"w-[25dvw]"}><QuizCreateSidebar/></div>
      </div>
    </>
  );
}