import { Input, Textarea } from "@nextui-org/input";

export type InputTextFields = {
  id: string;
  title: string;
  comment: string;
}
export default function InputText() {
  const dummyData = {
    "data":[
      {"id": "asd1",
        "title": "This is such a long ass dummy question, that it needs to be displayed in two completely different set of lines",
        "comment": "Please don't set your sister on fire"
      }
    ]
  }
dummyData.data.map(elem =>  elem as InputTextFields);


  return (

    <section
      className={"rounded-md border-1 mt-1 mx-12 flex text-center flex-nowrap flex-col align-middle place-items-center"}
      >


      <span className={"text-2xl"}>{dummyData.data[0].title}</span>
      <span>{dummyData.data[0].comment}</span>
      <Textarea
        isRequired={true}
        style={{background: "none"}}
        variant="bordered"
        placeholder="Enter your description"
        className={"max-w-ms mt-2 max-h-60 overflow-scroll text-justify"}
      />
    </section>

  )
}