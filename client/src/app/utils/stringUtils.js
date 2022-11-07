
export const parseStringArray = stringArray => {
    stringArray = stringArray.map(str => str.toLowerCase());
    return stringArray.join(", ")
}