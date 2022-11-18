import React from "react";
import {AuthContext} from "./AuthContext";
import {ROLE_USER} from "../utils/constans";
import {Navigate, Outlet, useLocation} from "react-router-dom";

export default function PrivateRoute({accessLevel}) {
    const role = accessLevel || ROLE_USER;
    const auth = React.useContext(AuthContext);
    const {pathname} = useLocation();

    return auth.role >= role ? <Outlet /> : <Navigate to={"/logowanie"} state={{next: pathname}} />;

}