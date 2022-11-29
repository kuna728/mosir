import React from "react";
import {ROLE_GUEST} from "../utils/constans";

export const AuthContext = React.createContext({
    role: ROLE_GUEST,
    legalName: null,
    token: null,
    login: (username, password, rememberMe) => {},
    logout: () => {}
})