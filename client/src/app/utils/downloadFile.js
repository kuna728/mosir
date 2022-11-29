import {BASE_URL} from "./constans";

export default function downloadFile(type, id, fileType, token) {
    // document.cookie = `token=${token}`;
    window.open(`${BASE_URL}/files/${fileType}/${type}/${id}?token=${token}`);
}