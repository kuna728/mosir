import React, {useContext} from "react";
import {
    Box,
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
import LoadingButton from '@mui/lab/LoadingButton';
import {Controller, useForm} from "react-hook-form";

export default function LoginView() {

    const [result, setResult] = React.useState(null);
    const [isLoading, setLoading] = React.useState(false);

    const { control, handleSubmit, formState: { errors } } = useForm({
        defaultValues: {
            username: "",
            password: "",
            rememberMe: false
        }
    })

    const auth = useContext(AuthContext);

    const navigate = useNavigate();
    const location = useLocation();

    const handleLogin = (data) => {
        console.log(data)
        const {username, password, rememberMe} = data
        setLoading(true);
        setResult(null);
        auth.login(username, password, rememberMe).then(isSuccess => {
            setLoading(false);
            if(isSuccess)
                navigate(location.state && location.state.next ? location.state.next : "/moje-bilety");
            else
                setResult("Podano zły login lub hasło")
        }).catch(e => {
            setLoading(false);
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
                            <Controller name="username" control={control}
                                        rules={{
                                            required: "Pole jest wymagane",
                                            minLength: {
                                                value: 4,
                                                message: "Login (email) powinien mieć minimum 4 znaki"
                                            },
                                            maxLength: {
                                                value: 100,
                                                message: "Login (email) nie może mieć więcej niż 100 znaków"
                                            }
                                        }}
                                        render={({field}) => (
                                            <TextField type="text" label="Login lub e-mail" variant="outlined"
                                                       error={errors.username} helperText={errors.username?.message}
                                                       {...field}
                                            />
                                        )}
                            />
                            <Controller name="password" control={control}
                                        rules={{
                                            required: "Pole jest wymagane",
                                            minLength: {
                                                value: 6,
                                                message: "Hasło powinno mieć minimum 6 znaków"
                                            },
                                            maxLength: {
                                                value: 60,
                                                message: "Hasło nie może mieć więcej niż 60 znaków"
                                            }
                                        }}
                                        render={({field}) => (
                                            <TextField type="password" label="Hasło" variant="outlined"
                                                       error={errors.password} helperText={errors.password?.message}
                                                       {...field}
                                            />
                                        )}
                            />
                            <Box display={{md: "flex"}} justifyContent="space-between" alignItems="center">
                                <Controller name="rememberMe" control={control}
                                            render={({field}) => (
                                                <FormControlLabel control={<Checkbox {...field}/>}
                                                                  label="Zapamiętaj mnie"
                                                />
                                            )}
                                />
                                <Typography display={{xs: "none", md: "inline"}} sx={{color: '#42a5f5'}}>
                                    <Link to="/resetuj-haslo">Nie pamiętasz hasła?</Link>
                                </Typography>
                            </Box>
                            <LoadingButton loading={isLoading} variant="contained" size="large" onClick={handleSubmit(handleLogin)}>
                                Zaloguj
                            </LoadingButton>
                            <Typography display={{xs: "block", md: "none"}} sx={{color: '#42a5f5'}}>
                                <Link to="/resetuj-haslo">Nie pamiętasz hasła?</Link>
                            </Typography>
                            <Typography sx={{color: '#42a5f5'}}><Link to="/rejestracja">Nie masz konta?</Link></Typography>
                        </Stack>
                    </Paper>
                </Grid>
            </Grid>
        </Box>
    )
}