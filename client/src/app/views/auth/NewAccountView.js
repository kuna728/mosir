import {
    Alert,
    Box,
    FormControl,
    FormHelperText,
    Grid, IconButton, InputAdornment,
    InputLabel, OutlinedInput,
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
import {Visibility, VisibilityOff} from "@mui/icons-material";
import useStateCallback from "../../utils/useStateCallback";


export default function NewAccountView() {

    const { control, handleSubmit, trigger, watch, formState: { errors } } = useForm();
    const [dateOfBirth, setDateOfBirth] = React.useState(null);
    const [isLoading, setLoading] = React.useState(false);
    const [result, setResult] = React.useState({success: null, message: null});
    const [showPassword, setShowPassword] = React.useState(false);
    const [notUniqueValues, setNotUniqueValues] = useStateCallback({phoneNumber: [], email: [], nationalRegistryNumber: [], username: []})


    const onSubmit = data => {
        setLoading(true);
        fetch(BASE_URL + "/api/auth/register", {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({...data, dateOfBirth: data.dateOfBirth.format('YYYY-MM-DD')})
        }).then(res => {
            setLoading(false);
            if(res.ok)
                return res.json();
            throw new Error();
        }).then(json => {
            if(json.success) {
                setResult({success: true, message: "Konto zostało utworzone pomyślnie. Aby je aktywować kliknij link wysłany na adres email podany przy rejestracji."});
            }
            else if(json.errors) {
                const notUniqueKeys = Object.entries(json.errors).filter(entry => entry[1].startsWith("Field must be unique.")).map(entry => entry[0]);
                const notUniqueValuesCopy = {...notUniqueValues};
                if(notUniqueKeys.includes("phoneNumber"))
                    notUniqueValuesCopy.phoneNumber.push(data.phoneNumber);
                if(notUniqueKeys.includes("email"))
                    notUniqueValuesCopy.email.push(data.email);
                if(notUniqueKeys.includes("nationalRegistryNumber"))
                    notUniqueValuesCopy.nationalRegistryNumber.push(data.nationalRegistryNumber);
                if(notUniqueKeys.includes("username"))
                    notUniqueValuesCopy.username.push(data.username);
                setNotUniqueValues(notUniqueValuesCopy, () => trigger(["phoneNumber", "email", "nationalRegistryNumber", "username"]));
            } else {
                throw new Error();
            }
        }).catch(() => {
            setLoading(false);
            setResult({success: false, message: "Coś poszło nie tak, spróbuj ponownie później"});
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
                            {result.message && (
                                <Grid item xs={12}>
                                    <Alert severity={result.success ? "success" : "error"}>
                                        {result.message}
                                    </Alert>
                                </Grid>
                            )}
                            <Grid item xs={12} md={4}>
                                <Controller name="email" control={control}
                                            rules={{
                                                required: REQUIRED_MESSAGE,
                                                pattern: {
                                                    value: /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/,
                                                    message: "Adres e-mail jest niepoprawny"
                                                },
                                                validate: {
                                                    emailUnique: v => !notUniqueValues.email.includes(v) || "Konto o podanym adresie e-mail jest już zarejestrowane w systemie"
                                                }
                                            }}
                                            render={({field}) => (
                                                <TextField type="text" label="Adres e-mail" variant="outlined" fullWidth
                                                           error={errors.email} helperText={errors.email?.message}
                                                           {...field} disabled={result.success}
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
                                                },
                                                validate: {
                                                    usernameUnique: v => !notUniqueValues.username.includes(v) || "Konto o podanej nazwie użytkownika jest już zarejestrowane w systemie"
                                                }
                                            }}
                                            render={({field}) => (
                                                <TextField type="text" label="Nazwa użytkownika" variant="outlined" fullWidth
                                                           error={errors.username} helperText={errors.username?.message}
                                                           {...field} disabled={result.success}
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
                                                },
                                                validate: {
                                                    phoneNumberUnique: v => !notUniqueValues.phoneNumber.includes(v) || "Konto o podanym numerze telefonu jest już zarejestrowane w systemie"
                                                }
                                            }}
                                            render={({field}) => (
                                                <TextField type="text" label="Numer telefonu" variant="outlined" fullWidth
                                                           error={errors.phoneNumber} helperText={errors.phoneNumber?.message}
                                                           {...field} disabled={result.success}
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
                                                <TextField
                                                    label="Hasło" type={showPassword ? 'text' : 'password'} fullWidth
                                                    error={errors.password} helperText={errors.password?.message} {...field}
                                                    disabled={result.success}
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
                            </Grid>
                            <Grid item xs={12} md={6}>
                                <Controller name="passwordConfirm" control={control}
                                            rules={{
                                                required: REQUIRED_MESSAGE,
                                                validate: v => v === watch("password") || "Hasla nie są identyczne"
                                            }}
                                            render={({field}) => (
                                                <TextField
                                                    label="Potwierdź hasło" type={showPassword ? 'text' : 'password'} fullWidth
                                                    error={errors.passwordConfirm} helperText={errors.passwordConfirm?.message} {...field}
                                                    disabled={result.success}
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
                                                           {...field} disabled={result.success}
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
                                                           {...field} disabled={result.success}
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
                                                    <Select {...field} label="Płeć" error={errors.gender}
                                                            disabled={result.success}
                                                    >
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
                                                    disabled={result.success}
                                                    renderInput={(params) => (
                                                        <TextField {...params} fullWidth
                                                                   error={errors.dateOfBirth}
                                                                   helperText={errors.dateOfBirth?.message}
                                                                   disabled={result.success}
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
                                                        gender: watch("gender")}) || "Numer PESEL jest nieprawidłowy",
                                                    peselUnique: v => !notUniqueValues.nationalRegistryNumber.includes(v) || "Konto o podanym numerze PESEL jest już zarejestrowane w systemie"
                                                }
                                            }}
                                            render={({field}) => (
                                                <TextField type="text" label="Numer PESEL" variant="outlined" fullWidth
                                                           error={errors.nationalRegistryNumber}
                                                           helperText={errors.nationalRegistryNumber?.message}
                                                           {...field} disabled={result.success}
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
                                                           {...field} disabled={result.success}
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
                                                           {...field} disabled={result.success}
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
                                                           {...field} disabled={result.success}
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
                                                           {...field} disabled={result.success}
                                                />
                                            )}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <LoadingButton variant="contained" size="large" onClick={handleSubmit(onSubmit)} fullWidth loading={isLoading} disabled={result.success}>
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