import { toast } from "sonner"
import { clientError } from "./types"

export async function handle(func : () => Promise<void>) {
    
    try{
        await func()
    }catch(e : any) {
        let messages : string[] = [e.messages || "unknown error"]
        if(e.messages) {
            const error = JSON.parse(e.messages)

            if(error?.type == "Client Error"){
                const clientError = error as clientError
                messages = clientError.messages
            }
        }

        toast("Error", {
            description : messages
        })
    }
}