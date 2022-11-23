import {BASE_URL} from "../../utils/constans";

export default async function tokenFetcher(url, token, init) {
    const _headers = init ? init.headers : null;
    const response = await fetch(BASE_URL + url, {...init, headers:
            {..._headers,  'Authorization': 'Bearer ' + token }});
    if(!response.ok)
        throw new Error(response.status);
    return response.json();
}