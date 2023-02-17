export default class AccountNotActiveError extends Error {
    constructor(message) {
        super(message);
        this.name = "AccountNotActiveError";
    }
}