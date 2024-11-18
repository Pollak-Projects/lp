import React from "react";

import { QuizData } from "@/src/types/question/quizData";
import QuizSelectPage from "@/src/components/quizPage/quizSelectPage";
import { auth } from "@/src/auth";
import { JWT } from "next-auth/jwt";

export default function Home() {

  const quizData: Array<QuizData> = [
    {
      id: "quiz1",
      name: "Sample Quiz",
      description: "This is a sample quiz description.",
      createdBy: "user1",
      createdAt: "2024-01-01T10:00:00Z",
      deadline: "2024-01-10T10:00:00Z",
      viewAfterSubmission: true,
      questionCheckboxes: [
        {
          id: "qcb1",
          quiz: "quiz1",
          title: "Select applicable options",
          options: [
            { id: "opt1", questionCheckbox: "qcb1", name: "Option 1", answer: true, createdBy: "user1" },
            { id: "opt2", questionCheckbox: "qcb1", name: "Option 2", answer: false, createdBy: "user1" },
            { id: "opt3", questionCheckbox: "qcb1", name: "Option 3", answer: true, createdBy: "user1" }
          ],
          createdBy: "user1"
        },
        {
          id: "qcb2",
          quiz: "quiz1",
          title: "Choose the correct options",
          options: [
            { id: "opt4", questionCheckbox: "qcb2", name: "Option A", answer: true, createdBy: "user2" },
            { id: "opt5", questionCheckbox: "qcb2", name: "Option B", answer: false, createdBy: "user2" },
            { id: "opt6", questionCheckbox: "qcb2", name: "Option C", answer: true, createdBy: "user2" }
          ],
          createdBy: "user2"
        },
        {
          id: "qcb3",
          quiz: "quiz1",
          title: "Identify the valid statements",
          options: [
            { id: "opt7", questionCheckbox: "qcb3", name: "Statement 1", answer: false, createdBy: "user3" },
            { id: "opt8", questionCheckbox: "qcb3", name: "Statement 2", answer: true, createdBy: "user3" },
            { id: "opt9", questionCheckbox: "qcb3", name: "Statement 3", answer: true, createdBy: "user3" }
          ],
          createdBy: "user3"
        }
      ],
      questionFiles: [
        { id: "qf1", quiz: "quiz1", title: "Upload your resume", fileId: "file1", createdBy: "user1" },
        { id: "qf2", quiz: "quiz1", title: "Upload your ID proof", fileId: "file2", createdBy: "user1" },
        { id: "qf3", quiz: "quiz1", title: "Upload your assignment", fileId: "file3", createdBy: "user2" }
      ],
      questionOrders: [
        {
          id: "qo1",
          quiz: "quiz1",
          title: "Arrange in order of importance",
          options: [
            { id: "ord1", questionOrder: "qo1", title: "First", place: 1, createdBy: "user1" },
            { id: "ord2", questionOrder: "qo1", title: "Second", place: 2, createdBy: "user1" },
            { id: "ord3", questionOrder: "qo1", title: "Third", place: 3, createdBy: "user1" }
          ],
          createdBy: "user1"
        },
        {
          id: "qo2",
          quiz: "quiz1",
          title: "Organize steps to complete the task",
          options: [
            { id: "ord4", questionOrder: "qo2", title: "Step 1", place: 1, createdBy: "user2" },
            { id: "ord5", questionOrder: "qo2", title: "Step 2", place: 2, createdBy: "user2" },
            { id: "ord6", questionOrder: "qo2", title: "Step 3", place: 3, createdBy: "user2" }
          ],
          createdBy: "user2"
        }
      ],
      questionPairCollection: [
        {
          id: "qpc1",
          quiz: "quiz1",
          createdBy: "user1",
          title: "Match the pairs",
          pairs: [
            { id: "pair1", belongsTo: "qpc1", createdBy: "user1", left: { id: "l1", content: "Apple" }, right: { id: "r1", content: "Fruit" } },
            { id: "pair2", belongsTo: "qpc1", createdBy: "user1", left: { id: "l2", content: "Carrot" }, right: { id: "r2", content: "Vegetable" } },
            { id: "pair3", belongsTo: "qpc1", createdBy: "user1", left: { id: "l3", content: "Dog" }, right: { id: "r3", content: "Animal" } }
          ]
        }
      ],
      questionRadios: [
        {
          id: "qr1",
          quiz: "quiz1",
          title: "Select the correct answer",
          options: [
            { id: "rad1", questionRadio: "qr1", title: "Option X", answer: false, createdBy: "user1" },
            { id: "rad2", questionRadio: "qr1", title: "Option Y", answer: true, createdBy: "user1" },
            { id: "rad3", questionRadio: "qr1", title: "Option Z", answer: false, createdBy: "user1" }
          ],
          createdBy: "user1"
        },
        {
          id: "qr2",
          quiz: "quiz1",
          title: "Choose the best answer",
          options: [
            { id: "rad4", questionRadio: "qr2", title: "Answer 1", answer: false, createdBy: "user2" },
            { id: "rad5", questionRadio: "qr2", title: "Answer 2", answer: true, createdBy: "user2" },
            { id: "rad6", questionRadio: "qr2", title: "Answer 3", answer: false, createdBy: "user2" }
          ],
          createdBy: "user2"
        }
      ],
      questionTexts: [
        { id: "qt1", quiz: "quiz1", title: "Describe your experience", answer: "I have 5 years of experience.", createdBy: "user1", createdAt: "2024-01-01T11:00:00Z" },
        { id: "qt2", quiz: "quiz1", title: "What are your goals?", answer: "To become a senior developer.", createdBy: "user2", createdAt: "2024-01-01T12:00:00Z" },
        { id: "qt3", quiz: "quiz1", title: "Provide a brief introduction", answer: "I am a software engineer.", createdBy: "user3", createdAt: "2024-01-01T13:00:00Z" }
      ],
      quizAnswers: ["Answer 1", "Answer 2", "Answer 3"]
    },
    {

      id: "quiz1",
      name: "Sample Quiz",
      description: "This is a sample quiz description.",
      createdBy: "user1",
      createdAt: "2024-01-01T10:00:00Z",
      deadline: "2024-01-10T10:00:00Z",
      viewAfterSubmission: true,
      questionCheckboxes: [
        {
          id: "qcb1",
          quiz: "quiz1",
          title: "Select applicable options",
          options: [
            { id: "opt1", questionCheckbox: "qcb1", name: "Option 1", answer: true, createdBy: "user1" },
            { id: "opt2", questionCheckbox: "qcb1", name: "Option 2", answer: false, createdBy: "user1" },
            { id: "opt3", questionCheckbox: "qcb1", name: "Option 3", answer: true, createdBy: "user1" }
          ],
          createdBy: "user1"
        },
        {
          id: "qcb2",
          quiz: "quiz1",
          title: "Choose the correct options",
          options: [
            { id: "opt4", questionCheckbox: "qcb2", name: "Option A", answer: true, createdBy: "user2" },
            { id: "opt5", questionCheckbox: "qcb2", name: "Option B", answer: false, createdBy: "user2" },
            { id: "opt6", questionCheckbox: "qcb2", name: "Option C", answer: true, createdBy: "user2" }
          ],
          createdBy: "user2"
        },
        {
          id: "qcb3",
          quiz: "quiz1",
          title: "Identify the valid statements",
          options: [
            { id: "opt7", questionCheckbox: "qcb3", name: "Statement 1", answer: false, createdBy: "user3" },
            { id: "opt8", questionCheckbox: "qcb3", name: "Statement 2", answer: true, createdBy: "user3" },
            { id: "opt9", questionCheckbox: "qcb3", name: "Statement 3", answer: true, createdBy: "user3" }
          ],
          createdBy: "user3"
        }
      ],
      questionFiles: [
        { id: "qf1", quiz: "quiz1", title: "Upload your resume", fileId: "file1", createdBy: "user1" },
        { id: "qf2", quiz: "quiz1", title: "Upload your ID proof", fileId: "file2", createdBy: "user1" },
        { id: "qf3", quiz: "quiz1", title: "Upload your assignment", fileId: "file3", createdBy: "user2" }
      ],
      questionOrders: [
        {
          id: "qo1",
          quiz: "quiz1",
          title: "Arrange in order of importance",
          options: [
            { id: "ord1", questionOrder: "qo1", title: "First", place: 1, createdBy: "user1" },
            { id: "ord2", questionOrder: "qo1", title: "Second", place: 2, createdBy: "user1" },
            { id: "ord3", questionOrder: "qo1", title: "Third", place: 3, createdBy: "user1" }
          ],
          createdBy: "user1"
        },
        {
          id: "qo2",
          quiz: "quiz1",
          title: "Organize steps to complete the task",
          options: [
            { id: "ord4", questionOrder: "qo2", title: "Step 1", place: 1, createdBy: "user2" },
            { id: "ord5", questionOrder: "qo2", title: "Step 2", place: 2, createdBy: "user2" },
            { id: "ord6", questionOrder: "qo2", title: "Step 3", place: 3, createdBy: "user2" }
          ],
          createdBy: "user2"
        }
      ],
      questionPairCollection: [
        {
          id: "qpc1",
          quiz: "quiz1",
          createdBy: "user1",
          title: "Match the pairs",
          pairs: [
            { id: "pair1", belongsTo: "qpc1", createdBy: "user1", left: { id: "l1", content: "Apple" }, right: { id: "r1", content: "Fruit" } },
            { id: "pair2", belongsTo: "qpc1", createdBy: "user1", left: { id: "l2", content: "Carrot" }, right: { id: "r2", content: "Vegetable" } },
            { id: "pair3", belongsTo: "qpc1", createdBy: "user1", left: { id: "l3", content: "Dog" }, right: { id: "r3", content: "Animal" } }
          ]
        }
      ],
      questionRadios: [
        {
          id: "qr1",
          quiz: "quiz1",
          title: "Select the correct answer",
          options: [
            { id: "rad1", questionRadio: "qr1", title: "Option X", answer: false, createdBy: "user1" },
            { id: "rad2", questionRadio: "qr1", title: "Option Y", answer: true, createdBy: "user1" },
            { id: "rad3", questionRadio: "qr1", title: "Option Z", answer: false, createdBy: "user1" }
          ],
          createdBy: "user1"
        },
        {
          id: "qr2",
          quiz: "quiz1",
          title: "Choose the best answer",
          options: [
            { id: "rad4", questionRadio: "qr2", title: "Answer 1", answer: false, createdBy: "user2" },
            { id: "rad5", questionRadio: "qr2", title: "Answer 2", answer: true, createdBy: "user2" },
            { id: "rad6", questionRadio: "qr2", title: "Answer 3", answer: false, createdBy: "user2" }
          ],
          createdBy: "user2"
        }
      ],
      questionTexts: [
        { id: "qt1", quiz: "quiz1", title: "Describe your experience", answer: "I have 5 years of experience.", createdBy: "user1", createdAt: "2024-01-01T11:00:00Z" },
        { id: "qt2", quiz: "quiz1", title: "What are your goals?", answer: "To become a senior developer.", createdBy: "user2", createdAt: "2024-01-01T12:00:00Z" },
        { id: "qt3", quiz: "quiz1", title: "Provide a brief introduction", answer: "I am a software engineer.", createdBy: "user3", createdAt: "2024-01-01T13:00:00Z" }
      ],
      quizAnswers: ["Answer 1", "Answer 2", "Answer 3"]
    },
    {

      id: "quiz1",
      name: "Sample Quiz",
      description: "This is a sample quiz description.",
      createdBy: "user1",
      createdAt: "2024-01-01T10:00:00Z",
      deadline: "2024-01-10T10:00:00Z",
      viewAfterSubmission: true,
      questionCheckboxes: [
        {
          id: "qcb1",
          quiz: "quiz1",
          title: "Select applicable options",
          options: [
            { id: "opt1", questionCheckbox: "qcb1", name: "Option 1", answer: true, createdBy: "user1" },
            { id: "opt2", questionCheckbox: "qcb1", name: "Option 2", answer: false, createdBy: "user1" },
            { id: "opt3", questionCheckbox: "qcb1", name: "Option 3", answer: true, createdBy: "user1" }
          ],
          createdBy: "user1"
        },
        {
          id: "qcb2",
          quiz: "quiz1",
          title: "Choose the correct options",
          options: [
            { id: "opt4", questionCheckbox: "qcb2", name: "Option A", answer: true, createdBy: "user2" },
            { id: "opt5", questionCheckbox: "qcb2", name: "Option B", answer: false, createdBy: "user2" },
            { id: "opt6", questionCheckbox: "qcb2", name: "Option C", answer: true, createdBy: "user2" }
          ],
          createdBy: "user2"
        },
        {
          id: "qcb3",
          quiz: "quiz1",
          title: "Identify the valid statements",
          options: [
            { id: "opt7", questionCheckbox: "qcb3", name: "Statement 1", answer: false, createdBy: "user3" },
            { id: "opt8", questionCheckbox: "qcb3", name: "Statement 2", answer: true, createdBy: "user3" },
            { id: "opt9", questionCheckbox: "qcb3", name: "Statement 3", answer: true, createdBy: "user3" }
          ],
          createdBy: "user3"
        }
      ],
      questionFiles: [
        { id: "qf1", quiz: "quiz1", title: "Upload your resume", fileId: "file1", createdBy: "user1" },
        { id: "qf2", quiz: "quiz1", title: "Upload your ID proof", fileId: "file2", createdBy: "user1" },
        { id: "qf3", quiz: "quiz1", title: "Upload your assignment", fileId: "file3", createdBy: "user2" }
      ],
      questionOrders: [
        {
          id: "qo1",
          quiz: "quiz1",
          title: "Arrange in order of importance",
          options: [
            { id: "ord1", questionOrder: "qo1", title: "First", place: 1, createdBy: "user1" },
            { id: "ord2", questionOrder: "qo1", title: "Second", place: 2, createdBy: "user1" },
            { id: "ord3", questionOrder: "qo1", title: "Third", place: 3, createdBy: "user1" }
          ],
          createdBy: "user1"
        },
        {
          id: "qo2",
          quiz: "quiz1",
          title: "Organize steps to complete the task",
          options: [
            { id: "ord4", questionOrder: "qo2", title: "Step 1", place: 1, createdBy: "user2" },
            { id: "ord5", questionOrder: "qo2", title: "Step 2", place: 2, createdBy: "user2" },
            { id: "ord6", questionOrder: "qo2", title: "Step 3", place: 3, createdBy: "user2" }
          ],
          createdBy: "user2"
        }
      ],
      questionPairCollection: [
        {
          id: "qpc1",
          quiz: "quiz1",
          createdBy: "user1",
          title: "Match the pairs",
          pairs: [
            { id: "pair1", belongsTo: "qpc1", createdBy: "user1", left: { id: "l1", content: "Apple" }, right: { id: "r1", content: "Fruit" } },
            { id: "pair2", belongsTo: "qpc1", createdBy: "user1", left: { id: "l2", content: "Carrot" }, right: { id: "r2", content: "Vegetable" } },
            { id: "pair3", belongsTo: "qpc1", createdBy: "user1", left: { id: "l3", content: "Dog" }, right: { id: "r3", content: "Animal" } }
          ]
        }
      ],
      questionRadios: [
        {
          id: "qr1",
          quiz: "quiz1",
          title: "Select the correct answer",
          options: [
            { id: "rad1", questionRadio: "qr1", title: "Option X", answer: false, createdBy: "user1" },
            { id: "rad2", questionRadio: "qr1", title: "Option Y", answer: true, createdBy: "user1" },
            { id: "rad3", questionRadio: "qr1", title: "Option Z", answer: false, createdBy: "user1" }
          ],
          createdBy: "user1"
        },
        {
          id: "qr2",
          quiz: "quiz1",
          title: "Choose the best answer",
          options: [
            { id: "rad4", questionRadio: "qr2", title: "Answer 1", answer: false, createdBy: "user2" },
            { id: "rad5", questionRadio: "qr2", title: "Answer 2", answer: true, createdBy: "user2" },
            { id: "rad6", questionRadio: "qr2", title: "Answer 3", answer: false, createdBy: "user2" }
          ],
          createdBy: "user2"
        }
      ],
      questionTexts: [
        { id: "qt1", quiz: "quiz1", title: "Describe your experience", answer: "I have 5 years of experience.", createdBy: "user1", createdAt: "2024-01-01T11:00:00Z" },
        { id: "qt2", quiz: "quiz1", title: "What are your goals?", answer: "To become a senior developer.", createdBy: "user2", createdAt: "2024-01-01T12:00:00Z" },
        { id: "qt3", quiz: "quiz1", title: "Provide a brief introduction", answer: "I am a software engineer.", createdBy: "user3", createdAt: "2024-01-01T13:00:00Z" }
      ],
      quizAnswers: ["Answer 1", "Answer 2", "Answer 3"]
    }
  ]

  async function fetch_selectQuizPages(token: JWT | undefined): Promise<QuizData[] | unknown> {
    if(token == undefined){
      throw new Error("Token is undefined")
    }
    try{
      const response = await fetch('https://localhost:52804/api/v1/quiz/all', {
        method: 'GET',
        headers: {
          "Content-Type": "application/json",
          Authorization: `none`,
        },
      })

      if (!response.ok) {
        throw new Error(response.statusText);
      }

      return await response.json();


    }
    catch (error){
      return error
    }
  }

  async function awaitSessionForSelect(){
    let session = await auth();
    if (!session || undefined) {
      throw new Error("Session is null")
    }
    return await fetch_selectQuizPages(session.token);
  }






  return (
    <>
      <QuizSelectPage quizData={quizData}  quizSelectResponse={awaitSessionForSelect()} />
    </>
  )
}
