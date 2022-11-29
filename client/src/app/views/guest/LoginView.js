import React, {useContext} from "react";
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
import {Link, useLocation, useNavigate} from "react-router-dom";
import {AuthContext} from "../../auth/AuthContext";

export default function LoginView() {

    const [username, setUsername] = React.useState("");
    const [password, setPassword] = React.useState("");
    const [rememberMe, setRememberMe] = React.useState(false);
    const [result, setResult] = React.useState(null);

    const auth = useContext(AuthContext);

    const navigate = useNavigate();
    const location = useLocation();

    const handleLogin = () => {
        setResult(null);
        auth.login(username, password, rememberMe).then(isSuccess => {
            if(isSuccess)
                navigate(location.state && location.state.next ? location.state.next : "/moje-bilety");
            else
                setResult("Podano zły login lub hasło")
        }).catch(e => {
            setResult("Coś poszło nie tak. Spróbuj ponownie później.")
        })
    }

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
                            { result && <Typography color="#d32f2f" variant="subtitle">{result}</Typography>}
                            <TextField type="text" label="Login lub e-mail" variant="outlined"
                                       value={username} onChange={e => setUsername(e.target.value)}
                            />
                            <TextField type="password" label="Hasło" variant="outlined"
                                       value={password} onChange={e => setPassword(e.target.value)}
                            />
                            <Box display={{md: "flex"}} justifyContent="space-between" alignItems="center">
                                <FormControlLabel control={<Checkbox checked={rememberMe} onChange={e => setRememberMe(!rememberMe)}/>}
                                                  label="Zapamiętaj mnie"
                                />
                                <Typography display={{xs: "none", md: "inline"}} sx={{color: '#42a5f5'}}>
                                    <Link to="/odzyskaj-haslo">Nie pamiętasz hasła?</Link>
                                </Typography>
                            </Box>
                            <Button variant="contained" size="large" onClick={handleLogin}>Zaloguj</Button>
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