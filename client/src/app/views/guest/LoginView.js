import React, {useContext} from "react";
import {
    Alert,
    Box,
    Checkbox,
    Link as LinkMUI,
    FormControlLabel,
    Grid, IconButton,
    Paper,
    Stack,
    TextField,
    Typography
} from "@mui/material";
import {Link, useLocation, useNavigate} from "react-router-dom";
import {AuthContext} from "../../auth/AuthContext";
import LoadingButton from '@mui/lab/LoadingButton';
import {Controller, useForm} from "react-hook-form";
import {Visibility, VisibilityOff} from "@mui/icons-material";
import AccountNotActiveError from "../../auth/AccountNotActiveError";
import {BASE_URL} from "../../utils/constans";
import RoleNotUserError from "../../auth/RoleNotUserError";

export default function LoginView() {

    const [result, setResult] = React.useState({severity: null, message: null});
    const [needsActivation, setNeedsActivation] = React.useState(false);
    const [activationEmailResend, setActivationEmailResend] = React.useState({success: null, message: null});
    const [isLoading, setLoading] = React.useState(false);
    const [showPassword, setShowPassword] = React.useState(false);

    const { control, handleSubmit, watch, formState: { errors } } = useForm({
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
        const {username, password, rememberMe} = data
        setLoading(true);
        auth.login(username, password, rememberMe).then(isSuccess => {
            setLoading(false);
            if(isSuccess)
                navigate(location.state && location.state.next ? location.state.next : "/moje-bilety");
            else
                setResult({severity: "error", message: "Podano zły login lub hasło"})
        }).catch(e => {
            setLoading(false);
            if(e instanceof AccountNotActiveError) {
                setNeedsActivation(true);
            } else if (e instanceof RoleNotUserError) {
                setResult({severity: "warning", message: "Portal na ten moment nie obsługuję kont pracowników. Jeżeli chcesz sprawdzić bilet klienta to możesz pobrać aplikację mobilną."});
            } else {
                setResult({severity: "error", message: "Coś poszło nie tak. Spróbuj ponownie później."});
            }
        })
    }

    const handleActivationEmailResend = () => {
        setLoading(true);
        fetch(BASE_URL + "/api/auth/activate/resend", {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({username: watch('username')})
        }).then(res => {
            setLoading(false);
            if(res.ok) {
                setActivationEmailResend({success: true, message: "Wiadomość email z linkiem aktywacyjnym została wysłana"});
                return;
            }
            throw new Error();
        }).catch(() => {
            setLoading(false);
            setActivationEmailResend({success: false, message: "Wiadomość email z linkiem aktywacyjnym nie mogła zostać wysłana, spróbuj ponownie później"});
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
                            {activationEmailResend.message && (
                                <Alert severity={activationEmailResend.success ? "success" : "error"}>
                                    {activationEmailResend.message}
                                </Alert>
                            )}
                            { needsActivation && !activationEmailResend.success && (
                                <Alert severity="warning">
                                    Konto nie zostało aktywowane, aby je aktywować kliknij w link wysłany na adres email podany przy rejestracji.
                                    Jeżeli wiadomość nie dotarła, lub link wygasł kliknij
                                    <LinkMUI onClick={handleActivationEmailResend} >&nbsp;tutaj&nbsp;</LinkMUI>
                                    aby wygenerować nowy link.
                                </Alert>
                            )}
                            { result.message && <Alert severity={result.severity}>{result.message}</Alert> }
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
                                                       {...field} disabled={needsActivation}
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
                                            <TextField
                                                label="Hasło" type={showPassword ? 'text' : 'password'} fullWidth
                                                error={errors.password} helperText={errors.password?.message} {...field}
                                                disabled={needsActivation}
                                                InputProps={{
                                                    endAdornment: (
                                                        <IconButton
                                                            aria-label="toggle password visibility"
                                                            onClick={() => setShowPassword(!showPassword)}
                                                            edge="end"
                                                        >
                                                            {showPassword ? <VisibilityOff /> : <Visibility />}
                                                        </IconButton>
                                                    ),
                                                }}
                                            />
                                        )}
                            />
                            <Box display={{md: "flex"}} justifyContent="space-between" alignItems="center">
                                <Controller name="rememberMe" control={control}
                                            render={({field}) => (
                                                <FormControlLabel control={<Checkbox {...field}/>}
                                                                  label="Zapamiętaj mnie" disabled={needsActivation}
                                                />
                                            )}
                                />
                                <Typography display={{xs: "none", md: "inline"}} sx={{color: '#42a5f5'}}>
                                    <Link to="/resetuj-haslo">Nie pamiętasz hasła?</Link>
                                </Typography>
                            </Box>
                            <LoadingButton loading={isLoading} variant="contained" size="large" type="submit"
                                           disabled={needsActivation} onClick={handleSubmit(handleLogin)}
                            >
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