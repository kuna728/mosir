
export const isLoading = (...args) => {
    return args.some(arg => arg.isLoading);
}

export const isError = (...args) => {
    return args.some(arg => arg.isError);
}