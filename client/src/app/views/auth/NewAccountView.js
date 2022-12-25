import {
    Box,
    FormControl,
    FormHelperText,
    Grid,
    InputLabel,
    Paper,
    Select,
    Stack,
    TextField,
    Typography
} from "@mui/material";
import React from "react";
import {Controller, useForm} from "react-hook-form";
import LoadingButton from "@mui/lab/LoadingButton";
import MenuItem from "@mui/material/MenuItem";
import {DatePicker} from "@mui/x-date-pickers";
import moment from "moment";
import peselValidator from 'pesel-validator';
import {Link} from "react-router-dom";
import {BASE_URL} from "../../utils/constans";


export default function NewAccountView() {

    const { control, handleSubmit, watch, formState: { errors } } = useForm();
    const [dateOfBirth, setDateOfBirth] = React.useState(null);

    console.log(watch("dateOfBirth") instanceof moment && watch("dateOfBirth").isValid());

    const onSubmit = data => {
        console.log(data);
        fetch(BASE_URL + "/api/auth/register", {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        }).then(res => {
            if(res.ok)
                return res.json();
        })
    }

    return (
        <Box sx={{mt: {xs: 10, md: 10}, mb: 5}}>
            <Grid container>
                <Grid item xs={0} md={1} xl={2}/>
                <Grid item xs={12} md={10} xl={8}>
                    <Paper sx={{padding: 2}}>
                        <Grid container spacing={2}>
                            <Grid item xs={12}>
                                <Typography variant="h3" component="h3" gutterBottom sx={{fontWeight: "300"}}>
                                    Rejestracja
                                </Typography>
                            </Grid>
                            <Grid item xs={12} md={4}>
                                <Controller name="email" control={control}
                                            rules={{
                                                required: REQUIRED_MESSAGE,
                                                pattern: {
                                                    value: /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/,
                                                    message: "Adres e-mail jest niepoprawny"
                                                }
                                            }}
                                            render={({field}) => (
                                                <TextField type="text" label="Adres e-mail" variant="outlined" fullWidth
                                                           error={errors.email} helperText={errors.email?.message}
                                                           {...field}
                                                />
                                            )}
                                />
                            </Grid>
                            <Grid item xs={12} md={4}>
                                <Controller name="username" control={control}
                                            rules={{
                                                required: REQUIRED_MESSAGE,
                                                minLength: {
                                                    value: 4,
                                                    message: "Nazwa użytkownika powinna mieć minimum 4 znaki"
                                                },
                                                maxLength: {
                                                    value: 100,
                                                    message: "Nazwa użytkownika nie może mieć więcej niż 100 znaków"
                                                }
                                            }}
                                            render={({field}) => (
                                                <TextField type="text" label="Nazwa użytkownika" variant="outlined" fullWidth
                                                           error={errors.username} helperText={errors.username?.message}
                                                           {...field}
                                                />
                                            )}
                                />
                            </Grid>
                            <Grid item xs={12} md={4}>
                                <Controller name="phoneNumber" control={control}
                                            rules={{
                                                required: REQUIRED_MESSAGE,
                                                pattern: {
                                                    value: /^\+\d{11}$/,
                                                    message: "Wprowadź poprawny numer telefonu wraz z numerem kierunkowym"
                                                }
                                            }}
                                            render={({field}) => (
                                                <TextField type="text" label="Numer telefonu" variant="outlined" fullWidth
                                                           error={errors.phoneNumber} helperText={errors.phoneNumber?.message}
                                                           {...field}
                                                />
                                            )}
                                            defaultValue="+48"
                                />
                            </Grid>
                            <Grid item xs={12} md={6}>
                                <Controller name="password" control={control}
                                            rules={{
                                                required: REQUIRED_MESSAGE,
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
                                                <TextField type="password" label="Hasło" variant="outlined" fullWidth
                                                           error={errors.password} helperText={errors.password?.message}
                                                           {...field}
                                                />
                                            )}
                                />
                            </Grid>
                            <Grid item xs={12} md={6}>
                                <Controller name="passwordConfirm" control={control}
                                            rules={{
                                                required: REQUIRED_MESSAGE,
                                                validate: v => v === watch("password") || "Hasla nie są identyczne"
                                            }}
                                            render={({field}) => (
                                                <TextField type="password" label="Potwierdź hasło" variant="outlined" fullWidth
                                                           error={errors.passwordConfirm} helperText={errors.passwordConfirm?.message}
                                                           {...field}
                                                />
                                            )}
                                />
                            </Grid>
                            <Grid item xs={12} md={5}>
                                <Controller name="firstName" control={control}
                                            rules={{
                                                required: REQUIRED_MESSAGE,
                                                minLength: {
                                                    value: 2,
                                                    message: "Imię powinno mieć minimum 2 znaki"
                                                },
                                                maxLength: {
                                                    value: 50,
                                                    message: "Imię nie może mieć więcej niż 50 znaków"
                                                }
                                            }}
                                            render={({field}) => (
                                                <TextField type="text" label="Imię" variant="outlined" fullWidth
                                                           error={errors.firstName} helperText={errors.firstName?.message}
                                                           {...field}
                                                />
                                            )}
                                />
                            </Grid>
                            <Grid item xs={12} md={5}>
                                <Controller name="lastName" control={control}
                                            rules={{
                                                required: REQUIRED_MESSAGE,
                                                minLength: {
                                                    value: 2,
                                                    message: "Nazwisko powinno mieć minimum 2 znaki"
                                                },
                                                maxLength: {
                                                    value: 50,
                                                    message: "Nazwisko nie może mieć więcej niż 70 znaków"
                                                }
                                            }}
                                            render={({field}) => (
                                                <TextField type="text" label="Nazwisko" variant="outlined" fullWidth
                                                           error={errors.lastName} helperText={errors.lastName?.message}
                                                           {...field}
                                                />
                                            )}
                                />
                            </Grid>
                            <Grid item xs={12} md={2}>
                                <FormControl fullWidth>
                                    <InputLabel style={{color: errors.gender && "#d32f2f"}}>Płeć</InputLabel>
                                    <Controller name="gender" control={control}
                                                rules={{ required: REQUIRED_MESSAGE }}
                                                render={({field}) => (
                                                    <Select {...field} label="Płeć" error={errors.gender}>
                                                        <MenuItem value=""></MenuItem>
                                                        <MenuItem value="M">Mężczyzna</MenuItem>
                                                        <MenuItem value="F">Kobieta</MenuItem>
                                                        <MenuItem value="O">Inne</MenuItem>
                                                    </Select>
                                                )}
                                    />
                                    <FormHelperText style={{color: "#d32f2f"}}>{errors.gender?.message}</FormHelperText>
                                </FormControl>
                            </Grid>
                            <Grid item xs={12} md={6}>
                                <Controller name="dateOfBirth" control={control}
                                            rules={{
                                                required: REQUIRED_MESSAGE,
                                                validate: {
                                                    valid: v => v.isValid() || "Wprowadź poprawną datę w formacie dd.mm.yyyy",
                                                    youngerThan100: v => moment().subtract(100, 'Y').isBefore(v)
                                                        || "Maksymalny wiek to 100 lat",
                                                    elderThan13: v => moment().subtract(13, 'Y').isAfter(v)
                                                        || "Minimalny wiek to 13 lat"
                                                }
                                            }}
                                            defaultValue={dateOfBirth}
                                            render={({field: {onChange, ...restField}}) => (
                                                <DatePicker
                                                    label="Data urodzenia"
                                                    onChange={e => {
                                                        onChange(e);
                                                        setDateOfBirth(e);
                                                    }}
                                                    {...restField}
                                                    renderInput={(params) => (
                                                        <TextField {...params} fullWidth
                                                                   error={errors.dateOfBirth}
                                                                   helperText={errors.dateOfBirth?.message}
                                                        />
                                                    )}
                                                />
                                            )}
                                />
                            </Grid>
                            <Grid item xs={12} md={6}>
                                <Controller name="nationalRegistryNumber" control={control}
                                            rules={{
                                                required: REQUIRED_MESSAGE,
                                                validate: {
                                                    dateOfBirthSet: v => watch("dateOfBirth") && true || "Najpierw należy podać datę urodzenia",
                                                    genderSet: v => watch("gender") && true || "Najpierw należy podać płeć",
                                                    peselValid: v => peselValidator(v,
                                                        { dateOfBirth: watch("dateOfBirth"),
                                                        gender: watch("gender")}) || "Numer PESEL jest nieprawidłowy"
                                                }
                                            }}
                                            render={({field}) => (
                                                <TextField type="text" label="Numer PESEL" variant="outlined" fullWidth
                                                           error={errors.nationalRegistryNumber}
                                                           helperText={errors.nationalRegistryNumber?.message}
                                                           {...field}
                                                />
                                            )}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <Controller name="addressLine1" control={control}
                                            rules={{
                                                required: REQUIRED_MESSAGE,
                                                minLength: {
                                                    value: 4,
                                                    message: "Adres powinien mieć minimum 4 znaki"
                                                },
                                                maxLength: {
                                                    value: 100,
                                                    message: "Adres nie może mieć więcej niż 100 znaków"
                                                }
                                            }}
                                            render={({field}) => (
                                                <TextField type="text" label="Adres (linijka 1)" variant="outlined" fullWidth
                                                           error={errors.addressLine1} helperText={errors.addressLine1?.message}
                                                           {...field}
                                                />
                                            )}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <Controller name="addressLine2" control={control}
                                            rules={{
                                                minLength: {
                                                    value: 4,
                                                    message: "Adres powinien mieć minimum 4 znaki"
                                                },
                                                maxLength: {
                                                    value: 100,
                                                    message: "Adres nie może mieć więcej niż 100 znaków"
                                                }
                                            }}
                                            render={({field}) => (
                                                <TextField type="text" label="Adres (linijka 2)" variant="outlined" fullWidth
                                                           error={errors.addressLine2} helperText={errors.addressLine2?.message}
                                                           {...field}
                                                />
                                            )}
                                />
                            </Grid>
                            <Grid item xs={12} md={4}>
                                <Controller name="zipCode" control={control}
                                            rules={{
                                                required: REQUIRED_MESSAGE,
                                                pattern: {
                                                    value: /^\d{2}-\d{3}$/,
                                                    message: "Wprowadź poprawny kod pocztowy w formacie XX-XXX"
                                                }
                                            }}
                                            render={({field}) => (
                                                <TextField type="text" label="Kod pocztowy" variant="outlined" fullWidth
                                                           error={errors.zipCode} helperText={errors.zipCode?.message}
                                                           {...field}
                                                />
                                            )}
                                />
                            </Grid>
                            <Grid item xs={12} md={8}>
                                <Controller name="city" control={control}
                                            rules={{
                                                required: REQUIRED_MESSAGE,
                                                minLength: {
                                                    value: 3,
                                                    message: "Miasto powinno mieć minimum 3 znaki"
                                                },
                                                maxLength: {
                                                    value: 50,
                                                    message: "Miasto nie może mieć więcej niż 100 znaków"
                                                }
                                            }}
                                            render={({field}) => (
                                                <TextField type="text" label="Miasto" variant="outlined" fullWidth
                                                           error={errors.city} helperText={errors.city?.message}
                                                           {...field}
                                                />
                                            )}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <LoadingButton variant="contained" size="large" onClick={handleSubmit(onSubmit)} fullWidth>
                                    Utwórz konto
                                </LoadingButton>
                            </Grid>
                            <Grid item xs={12}>
                                <Typography sx={{color: '#42a5f5'}}>
                                    <Link to="/logowanie">Masz już konto?</Link>
                                </Typography>
                            </Grid>
                        </Grid>
                    </Paper>
                </Grid>
            </Grid>
        </Box>
    )
}

const REQUIRED_MESSAGE = "Pole jest wymagane";