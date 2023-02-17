import {Controller, useForm} from "react-hook-form";
import {Box, Grid, Paper, Stack, TextField, Typography} from "@mui/material";
import LoadingButton from "@mui/lab/LoadingButton";
import React from "react";
import {BASE_URL} from "../../utils/constans";
import {Alert} from "@mui/material";

export default function ResetPasswordView() {

    const [result, setResult] = React.useState({success: null, message: null});
    const [isLoading, setLoading] = React.useState(false);

    const { control, handleSubmit, formState: { errors } } = useForm();

    const onSubmit = data => {
        setLoading(true);
        fetch(BASE_URL + "/api/auth/reset/generate", {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        }).then(res => {
            setLoading(false);
            if(res.ok)
                setResult({success: true, message: "Na podany adres email został wysłany link do zresetowania hasła"});
            else if (res.status === 404)
                setResult({success: false, message: "Konto o podanym adresie email nie istnieje"});
            else
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
                                Resetowanie hasła
                            </Typography>
                            {result.message && (
                                <Alert severity={result.success ? "success" : "error"}>
                                    {result.message}
                                </Alert>
                            )}
                            <Controller name="email" control={control}
                                        rules={{
                                            required: "Pole jest wymagane",
                                            pattern: {
                                                value: /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/,
                                                message: "Adres e-mail jest niepoprawny"
                                            }
                                        }}
                                        render={({field}) => (
                                            <TextField type="text" label="Adres e-mail" variant="outlined" disabled={result.success}
                                                       error={errors.email} helperText={errors.email?.message}
                                                       {...field}
                                            />
                                        )}
                            />
                            <LoadingButton variant="contained" size="large" onClick={handleSubmit(onSubmit)} loading={isLoading} disabled={result.success}>
                                Wyślij link do resetowania hasła
                            </LoadingButton>
                        </Stack>
                    </Paper>
                </Grid>
            </Grid>
        </Box>
    )
}