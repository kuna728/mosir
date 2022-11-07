import React from "react";
import {Box, Button, Grid, Typography} from "@mui/material";
import error_image from "../../assets/images/error.png"
import {useNavigate} from "react-router-dom";
import {NOT_FOUND, NOT_IMPLEMENTED} from "../../utils/constans";
import Image from "mui-image";

export default function ErrorView({status}) {

    const navigate = useNavigate();

    const statusMessage = status === NOT_FOUND ? "Strona nie została odnaleziona." : (
        status === NOT_IMPLEMENTED ? "Funkcjonalność nie została jeszcze zaimplementowana, przepraszamy."
            : "Coś poszło nie tak. Spróbuj ponownie później."
    )


    return (
        <Grid container sx={{mt: {sx: 2, md: 5}}} spacing="50" justifyContent="space-around" alignItems="center">
            <Grid item>
                <Typography variant="h2" component="h1" gutterBottom>
                    Oops
                </Typography>
                <Typography variant="h5" component="h2" gutterBottom>
                    {statusMessage}
                </Typography>
                <Box sx={{mt:3}}>
                    <Button variant="contained" size="large" sx={{mr: 2}} onClick={() => navigate(-1)}>
                        Powrót
                    </Button>
                    <Button variant="contained" size="large" color="secondary" onClick={() => navigate("/kontakt")}>
                        Kontakt
                    </Button>
                </Box>
            </Grid>
            <Grid item>
                <Image src={error_image} sx={{width: {xs:300, md:500}, height: "auto"}} duration={0} shift="top" distance="2rem" shiftDuration={320}/>
            </Grid>
        </Grid>
    )
}