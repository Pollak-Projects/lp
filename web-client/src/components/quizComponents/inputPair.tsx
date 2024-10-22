import { QuestionPair } from "@/src/types/questionPair";
import React, { useRef, useState } from "react";


export default function InputPair({question}:{question: QuestionPair}) {

  const [isPairLeft, setPairLeft] = useState(question.value.left)

  const dragDataLeft = useRef<number>(0);
  const draggedOverDataLeft = useRef<number>(0);

  // function to handle the state when drag and dropping
  function handleSortLeft() {
    // flippity floppity, value goin orderly
    const dataClone = [...isPairLeft];
    const temp = dataClone[dragDataLeft.current];
    dataClone[dragDataLeft.current] = dataClone[draggedOverDataLeft.current];
      /*stolen code no. 649655*/
    dataClone[draggedOverDataLeft.current] = temp;
    setPairLeft(dataClone);
  }

  //left part of the pair
  const pair_elements_left = isPairLeft.map((value, index: number) => {
    return (
      <>
        <div
          className={
          "w-full h-24 flex justify-center items-center  border rounded-md p-2 m-2 overflow-scroll cursor-move"
        }
          draggable
          key={value.id}
          onDragStart={() => (dragDataLeft.current = index)}
          onDragEnter={() => (draggedOverDataLeft.current = index)}
          onDragEnd={handleSortLeft}
          onDragOver={(e) => e.preventDefault()}
        >
          <span>{value.content}</span>
        </div>
      </>
    )
  })

  const [isPairRight, setPairRight] = useState(question.value.right)

  const dragDataRight = useRef<number>(0);
  const draggedOverDataRight = useRef<number>(0);

  //right column draggable elements
  const [isDraggingRight, setIsDraggingRight] = useState(false);
  const [droppedRight, setDroppedRight] = useState(false);

  // function to handle the state when drag and dropping
  function handleSortRight() {
    // flippity floppity, value goin orderly
    const dataClone = [...isPairRight];
    const temp = dataClone[dragDataRight.current];
    dataClone[dragDataRight.current] = dataClone[draggedOverDataRight.current];
    /*stolen code no. 649656*/
    dataClone[draggedOverDataRight.current] = temp;
    setPairRight(dataClone);
    setIsDraggingRight(false);
  }

  //right part of the pair
  const pair_elements_right = isPairRight.map((value, index: number) => {
    return (
      <>
        <div
          className={
          "w-full h-24 flex justify-center items-center border rounded-md p-2 m-2 overflow-scroll cursor-move" +
            ""
        }
          draggable
          key={value.id}
          onDragStart={(e) => {
            setIsDraggingRight(true);
            dragDataRight.current = index
            e.dataTransfer.effectAllowed = "move"
          }}
          onDragEnter={() => (draggedOverDataRight.current = index)}
          onDragEnd={handleSortRight}
          onDragOver={(e) => e.preventDefault()}
          onDrop={(e) => {
            e.preventDefault()
            setDroppedRight(true)
          }}
        >
          <span className={""}>{value.content}</span>
        </div>
      </>
    )
  })


  return (
    <>
      <section
        className={
          "rounded-md border w-full h-fit mb-2 mt-1 mx-12 flex text-center flex-col place-items-center"
        }>
      <span className={"text-2xl"}>{question.title}</span>
        <span>{question.comment}</span>
        <div
          className={
            "border-t w-full h-fit flex flex-row place-items-center my-1 py-1"
          }
        >
          <div className={"flex-1 border-r-[1px] px-2 h-fit flex flex-col justify-start items-center"}

          >
            {pair_elements_left}
          </div>
          <div className={"flex-1 px-2 h-fit flex flex-col justify-start items-center "}>
            {pair_elements_right}
          </div>
        </div>
      </section>
    </>
  )
}