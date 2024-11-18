import { QuestionOrder } from "@/src/types/question/questionOrder";
import React, { useEffect, useRef, useState } from "react";

export default function InputOrder({ question }: { question: QuestionOrder }) {

  //TODO: random generate the columns on server side
  const shuffle = (array: Array<{
    id:string,
    content:string
  }>) => {
    for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
  };

  const [loading, setLoading] = useState(true);

  const [isOrder, setOrder] = useState(question.value); // the value get returned back to the state from the ordering list

  useEffect( () => {
    setOrder(shuffle([...question.value]));
    setLoading(false)
  }, question.value);

  const dragData = useRef<number>(0);
  const draggedOverData = useRef<number>(0);

  const [dragging, setDragging] = useState(false);
  const [dropped, setDropped] = useState(false);

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
        className={"w-full flex-1 border rounded-md p-2 m-2 cursor-move"}
        draggable
        key={value.id}
        onDragStart={(e) => {
          setDragging(true)
          dragData.current = index
          e.dataTransfer.effectAllowed = "move"
        }}
        onDragEnter={() => (draggedOverData.current = index)}
        onDragEnd={handleSort}
        onDragOver={(e) => e.preventDefault()}
        onDrop={(e) => {
          e.preventDefault()
          setDropped(true)
        }}
      >
        <span>{value.content}</span>
      </div>
    );
  });

  if (loading) {
    return <div>Loading...</div>; // Optionally, display a loading state while shuffling
  }

  return (
    <>
      <section className="bg-content2 rounded-xl w-full pt-5 mb-2 mt-1 mx-12 flex text-center flex-nowrap flex-col align-middle place-items-center">
        <span className={"text-2xl"}>{question.title}</span>
        <span>{question.comment}</span>
        <div
          className={"border-t border-white-opacity70 w-full flex flex-col place-items-center my-1 px-36 py-3"}
        >
          {order_elements}
        </div>
      </section>
    </>
  );
}
