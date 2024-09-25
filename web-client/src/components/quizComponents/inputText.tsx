import { Input } from "@nextui-org/input";
import { json } from "node:stream/consumers";

 export type InputFields = {
  id: string;
  title: string;
  comment: string;
}
export default function InputText() {
  const dummyData = {
    "data":[
      {"id": "asd1", "title": "This is such a long ass dummy question, that it needs to be displayed in two completely different set of lines", "comment": "Please don't set your sister on fire"}
    ]
  }
dummyData.data.map(elem =>  elem as InputFields);


  return (

    <section className={"rounded-md mt-1 mx-12 flex text-center flex-nowrap flex-col align-middle place-items-center"}>
      <span className={"text-2xl"}>{dummyData.data[0].title}</span>
      <span>{dummyData.data[0].comment}</span>
      <Input
        className={"mt-2"}
        type="text"
        labelPlacement={"inside"}
        isRequired={true}
        variant={"bordered"}
        style={{background: "none"}}
        autoComplete={"off"}/>

    </section>

  )
}