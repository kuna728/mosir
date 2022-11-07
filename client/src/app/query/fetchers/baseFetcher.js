import {BASE_URL} from "../../utils/constans";

export default async function baseFetcher(url) {
    const response = await fetch(BASE_URL + url);
    if(!response.ok)
        throw new Error(response.status);
    return response.json();
}