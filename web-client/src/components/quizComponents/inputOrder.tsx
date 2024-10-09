import { QuestionOrder } from "@/src/types/questionOrder";
import React, { useRef, useState } from "react";

export default function InputOrder({ question }: { question: QuestionOrder }) {
  const [isOrder, setOrder] = useState(question.value); // the value get returned back to the state from the ordering list

  const dragData = useRef<number>(0);
  const draggedOverData = useRef<number>(0);

  function handleSort() {
    // flippity floppity, value goin orderly
    const dataClone = [...isOrder];
    const temp = dataClone[dragData.current];
    dataClone[dragData.current] =
      /*stolen code no. 649654*/ dataClone[draggedOverData.current];
    dataClone[draggedOverData.current] = temp;
    setOrder(dataClone);
  }

  const order_elements = isOrder.map((value, index: number) => {
    return (
      <div
        className={"w-fit border rounded-md p-2 m-2 cursor-pointer"}
        draggable
        key={value.id}
        onDragStart={() => (dragData.current = index)}
        onDragEnter={() => (draggedOverData.current = index)}
        onDragEnd={handleSort}
        onDragOver={(e) => e.preventDefault()}
      >
        <span>{value.content}</span>
      </div>
    );
  });

  return (
    <>
      <section className="rounded-md border-1 w-full mb-2 mt-1 mx-12 flex text-center flex-nowrap flex-col align-middle place-items-center">
        <span className={"text-2xl"}>{question.title}</span>
        <span>{question.comment}</span>
        <div
          className={
            "border-t-1 w-full flex flex-col place-items-center my-1 py-1"
          }
        >
          {order_elements}
        </div>
      </section>
    </>
  );
}
