import React from "react";
import {BASE_URL, ROLE_GUEST} from "../utils/constans";
import {AuthContext} from "./AuthContext";

export default function AuthProvider({children}) {
    const [role, setRole] = React.useState(localStorage.getItem("role") || ROLE_GUEST);
    const [legalName, setLegalName] = React.useState(localStorage.getItem("legalName"));
    const [token, setToken] = React.useState(localStorage.getItem("token"));

    const login = (username, password, rememberMe) => fetch(BASE_URL + "/api/auth/login", {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({username, password})
    }).then(res => {
        if(res.ok)
            return res.json();
        throw new Error(res.status);
    }).then(json => {
        if(json.success) {
            setRole(json.role);
            setLegalName(`${json.firstName} ${json.lastName}`);
            setToken(json.token);
            if(rememberMe) {
                localStorage.setItem("role", json.role);
                localStorage.setItem("legalName", `${json.firstName} ${json.lastName}`)
                localStorage.setItem("token", json.token);
            }
        }
        return json.success;
    })

    const logout = () => {
        setRole(ROLE_GUEST);
        setToken(null);
        localStorage.removeItem("role");
        localStorage.removeItem("legalName");
        localStorage.removeItem("token");
    }

    return (
        <AuthContext.Provider value={{role, legalName, token, login, logout}}>
            {children}
        </AuthContext.Provider>
    )
}