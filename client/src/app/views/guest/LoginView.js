import React from "react";
import {
    Box,
    Button,
    Checkbox,
    FormControlLabel,
    Grid,
    Paper,
    Stack,
    TextField,
    Typography
} from "@mui/material";
import {Link} from "react-router-dom";
import {useForm} from "react-hook-form";

export default function LoginView() {

    const { handleSubmit, reset, control } = useForm();

    const testHandler = () => fetch("http://localhost:8080/api/auth/login", {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({username: "test", password: "test"})
    }).then(res => res.json()).then(json => console.log(json));

    return (
        <Box sx={{mt: {xs: 10, md: 10}, mb: 5}}>
            <Grid container>
                <Grid item xs={0} md={3} xl={4}/>
                <Grid item xs={12} md={6} xl={4}>
                    <Paper sx={{padding: 2}}>
                        <Stack spacing={2}>
                            <Typography variant="h3" component="h3" gutterBottom sx={{fontWeight: "300"}}>
                                Logowanie
                            </Typography>
                            <TextField type="text" label="Login lub e-mail" variant="outlined" />
                            <TextField type="password" label="Hasło" variant="outlined" />
                            <Box display={{md: "flex"}} justifyContent="space-between" alignItems="center">
                                <FormControlLabel control={<Checkbox/>} label="Zapamiętaj mnie" />
                                <Typography display={{xs: "none", md: "inline"}} sx={{color: '#42a5f5'}}>
                                    <Link to="/odzyskaj-haslo">Nie pamiętasz hasła?</Link>
                                </Typography>
                            </Box>
                            <Button variant="contained" size="large" onClick={testHandler}>Zaloguj</Button>
                            <Typography display={{xs: "block", md: "none"}} sx={{color: '#42a5f5'}}>
                                <Link to="/odzyskaj-haslo">Nie pamiętasz hasła?</Link>
                            </Typography>
                            <Typography sx={{color: '#42a5f5'}}><Link to="/rejestracja">Nie masz konta?</Link></Typography>
                        </Stack>
                    </Paper>
                </Grid>
            </Grid>
        </Box>
    )
}