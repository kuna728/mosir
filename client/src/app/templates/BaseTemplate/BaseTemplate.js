import React from "react";
import {Outlet} from "react-router-dom";
import TopBar from "./components/TopBar";
import "../../assets/styles/base_template.css";

export default function BaseTemplate() {
    return (
        <div>
            <TopBar />
            <Outlet />
        </div>
    )
}