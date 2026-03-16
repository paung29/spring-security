import "server-only"

export async function request(path:string, init : RequestInit = {}) {

    const endpoint = `${process.env.REST_API}/${path}`

    const response = await fetch(endpoint, init)

    if(!response.ok) {
        const message = await response.json() as string[]
        throw JSON.stringify({
            type : "Client Error",
            messages : message
        })
    }

    return response
    
}