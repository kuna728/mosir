import React from "react";
import {Outlet} from "react-router-dom";
import TopBar from "./components/TopBar";
import "../../assets/styles/base_template.css";
import Footer from "./components/Footer";
import {Box, Container} from "@mui/material";

export default function BaseTemplate() {
    return (
        <Box sx={{display: "flex", flexDirection: "column"}}>
            <Box sx={{minHeight: '100vh'}}>
                <TopBar />
                <Container maxWidth="xl" sx={{mt:4}}>
                    <Outlet />
                </Container>
            </Box>
            <Footer />
        </Box>
    )
}