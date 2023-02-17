import {Alert, Box, Grid, Paper, Stack, Typography} from "@mui/material";
import React, {useState} from "react";
import LoadingButton from "@mui/lab/LoadingButton";
import {useNavigate, useSearchParams} from "react-router-dom";
import {BASE_URL} from "../../utils/constans";

export default function ActivateAccountView() {
    const [searchParams] = useSearchParams();
    const navigate = useNavigate();

    const [isLoading, setLoading] = React.useState(false);
    const [result, setResult] = useState({severity: null, message: null});

    React.useEffect(() => {
        const token = searchParams.get("token");
        if(!token) {
            setResult({severity: "error", message: "Link jest nieprawidłowy"})
            return;
        }
        setLoading(true)
        fetch(BASE_URL + "/api/auth/activate", {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({token})
        }).then(res => {
            setLoading(false);
            if(res.ok)
                return res.json();
            throw new Error();
        }).then(json => {
            if(json.success) {
                setResult({severity: "success", message: "Konto zostało aktywowane, możesz się zalogować"})
            } else if (json.status === "INVALID") {
                setResult({severity: "error", message: "Token jest nieprawidłowy"})
            } else if (json.status === "DROPPED") {
                setResult({severity: "error", message: "Token jest nieaktualny - został wygenerowany nowy token"})
            } else if (json.status === "USED") {
                setResult({severity: "warning", message: "Konto jest już aktywne, możesz się zalogować"})
            } else {
                setResult({severity: "error", message: "Token wygasł, ale możesz wygenerować nowy token"})
            }
        }).catch(() => {
            setLoading(false);
            setResult({severity: "error", message: "Coś poszło nie tak, spróbuj ponownie później"})
        })
    }, []);

    return (
        <Box sx={{mt: {xs: 10, md: 10}, mb: 5}}>
            <Grid container>
                <Grid item xs={0} md={3} xl={4}/>
                <Grid item xs={12} md={6} xl={4}>
                    <Paper sx={{padding: 2}}>
                        <Stack spacing={2}>
                            <Typography variant="h3" component="h3" sx={{fontWeight: "300"}} gutterBottom>
                                Aktywacja konta
                            </Typography>
                            {result.message && (
                                <Alert severity={result.severity}>
                                    {result.message}
                                </Alert>
                            )}
                            <LoadingButton variant="contained" size="large" loading={isLoading} onClick={() => navigate('/logowanie')}>
                                Przejdź do logowania
                            </LoadingButton>
                        </Stack>
                    </Paper>
                </Grid>
            </Grid>
        </Box>
    )
}