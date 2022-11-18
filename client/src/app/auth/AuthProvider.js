import React from "react";
import {BASE_URL, ROLE_GUEST} from "../utils/constans";
import {AuthContext} from "./AuthContext";

export default function AuthProvider({children}) {
    const [role, setRole] = React.useState(ROLE_GUEST);
    const [token, setToken] = React.useState(null);

    const login = (username, password, rememberMe) => fetch(BASE_URL + "/api/login", {
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
            setToken(json.token);
            if(rememberMe)
                localStorage.setItem("token", json.token);
        }
        return json.success;
    })

    const logout = () => {
        setRole(ROLE_GUEST);
        setToken(null);
        localStorage.removeItem("token");
    }

    return (
        <AuthContext.Provider value={{role, token, login, logout}}>
            {children}
        </AuthContext.Provider>
    )
}