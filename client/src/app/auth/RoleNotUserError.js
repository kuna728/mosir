export default class RoleNotUserError extends Error {
    constructor(message) {
        super(message);
        this.name = "RoleNotUserError";
    }
}