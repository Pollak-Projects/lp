import { Spinner } from "@nextui-org/spinner";




export default function Loading({color}:
{color: string | undefined}) {
  // You can add any UI inside Loading, including a Skeleton.
  if (!color) {
    return <Spinner color={"default"}/>
  }
  if(color){
    // @ts-ignore
    return <Spinner color={color}/>
  }
}