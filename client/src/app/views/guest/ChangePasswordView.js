import {Link, useSearchParams} from "react-router-dom";
import {Alert, Box, Grid, Paper, Stack, TextField, Typography} from "@mui/material";
import React, {useState} from "react";
import {BASE_URL} from "../../utils/constans";
import {Controller, useForm} from "react-hook-form";
import LoadingButton from "@mui/lab/LoadingButton";

const PASSWORD_RESET_OPERATION_TYPE = "PASSWORD_RESET";

export default function ChangePasswordView() {
    const [searchParams] = useSearchParams();

    const [email, setEmail] = React.useState(null);
    const [result, setResult] = useState({success: null, message: null});
    const [isLoading, setLoading] = React.useState(false);

    const { control, handleSubmit, formState: { errors } } = useForm();

    React.useEffect(() => {
        const token = searchParams.get("token");
        if(!token) {
            setResult({success: false, message: "Link jest nieprawidłowy"})
            return;
        }
        setLoading(true);
        fetch(BASE_URL + "/api/auth/reset/verify", {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({tokenOperationType: PASSWORD_RESET_OPERATION_TYPE, token})
        }).then(res => {
            setLoading(false);
            if(res.ok)
                return res.json();
            throw new Error();
        }).then(json => {
            if(json.valid) {
                setEmail(json.email);
            } else if(json.tokenStatus === "EXPIRED") {
                setResult({success: false, message: "Token wygasł, ale możesz wygenerować nowy token"});
            } else if(json.tokenStatus === "USED") {
                setResult({success: false, message: "Token został już wykorzystany"});
            } else if(json.tokenStatus === "DROPPED") {
                setResult({success: false, message: "Token jest nieaktualny - został wygenerowany nowy token"});
            } else {
                setResult({success: false, message: "Token jest nieprawidłowy"});
            }
        }).catch(() => {
            setLoading(false);
            setResult({success: false, message: "Coś poszło nie tak, spróbuj ponownie później"});
        })
    }, []);

    const onSubmit = data => {
        setLoading(true);
        fetch(BASE_URL + "/api/auth/reset/finalize", {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({token: searchParams.get("token"), ...data})
        }).then(res => {
            setLoading(false);
            if(res.ok) {
                setResult({success: true, message: "Hasło zostało zmienione pomyślnie"});
                return;
            }
            throw new Error();
        }).catch(() => {
            setLoading(false);
            setResult({success: false, message: "Coś poszło nie tak, spróbuj ponownie później"});
        })
    }

    return (
        <Box sx={{mt: {xs: 10, md: 10}, mb: 5}}>
            <Grid container>
                <Grid item xs={0} md={3} xl={4}/>
                <Grid item xs={12} md={6} xl={4}>
                    <Paper sx={{padding: 2}}>
                        <Stack spacing={2}>
                            <Typography variant="h3" component="h3" sx={{fontWeight: "300"}} gutterBottom>
                                Zmiana hasła
                            </Typography>
                            {result.message && (
                                <Alert severity={result.success ? "success" : "error"}>
                                    {result.message}
                                </Alert>
                            )}
                            {email && <TextField type="text" variant="outlined" disabled value={email} />}
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
                                            },
                                            pattern: {
                                                value: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,60}$/,
                                                message: "Hasło powinno zawierać małe i wielkie litery oraz cyfry"
                                            }
                                        }}
                                        render={({field}) => (
                                            <TextField type="password" label="Hasło" variant="outlined" disabled={result.message}
                                                       error={errors.password} helperText={errors.password?.message}
                                                       {...field}
                                            />
                                        )}
                            />
                            <LoadingButton loading={isLoading} variant="contained" size="large" disabled={result.message} onClick={handleSubmit(onSubmit)}>
                                Zmień hasło
                            </LoadingButton>
                            <Typography sx={{color: '#42a5f5'}}>
                                {result.success ? <Link to="/logowanie">Logowanie</Link> :
                                    <Link to="/resetuj-haslo">Wygeneruj nowy link</Link>
                                }
                            </Typography>
                        </Stack>
                    </Paper>
                </Grid>
            </Grid>
        </Box>
    )
}