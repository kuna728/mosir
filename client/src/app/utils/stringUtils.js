
export const parseStringArray = stringArray => {
    stringArray = stringArray.map(str => str.toLowerCase());
    return stringArray.join(", ")
}

export const capitalizeFirstLetter = string => string.charAt(0).toUpperCase() + string.slice(1)