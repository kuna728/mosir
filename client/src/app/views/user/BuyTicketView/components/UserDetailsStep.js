import {Grid, TextField} from "@mui/material";

export default function UserDetailsStep({userDetails}) {

    return (
        <Grid container spacing={2}>
            <Grid item xs={12} md={6}>
                <TextField variant="standard" fullWidth label="Imię" defaultValue={userDetails.firstName} disabled />
            </Grid>
            <Grid item xs={12} md={6}>
                <TextField variant="standard" fullWidth label="Nazwisko" defaultValue={userDetails.lastName} disabled />
            </Grid>
            <Grid item xs={12} md={6}>
                <TextField variant="standard" fullWidth label="Adres e-mail" defaultValue={userDetails.email} disabled />
            </Grid>
            <Grid item xs={12} md={6}>
                <TextField variant="standard" fullWidth label="Numer telefoonu" defaultValue={userDetails.phoneNumber} disabled />
            </Grid>
            <Grid item xs={12} md={6}>
                <TextField variant="standard" fullWidth label="Adres linijka 1" defaultValue={userDetails.addressLine1} disabled />
            </Grid>
            <Grid item xs={12} md={6}>
                <TextField variant="standard" fullWidth label="Adres linijka 2" defaultValue={userDetails.addressLine2} disabled />
            </Grid>
            <Grid item xs={12} md={6}>
                <TextField variant="standard" fullWidth label="Kod pocztowy" defaultValue={userDetails.zipCode} disabled />
            </Grid>
            <Grid item xs={12} md={6}>
                <TextField variant="standard" fullWidth label="Miasto" defaultValue={userDetails.city} disabled />
            </Grid>
        </Grid>
    )
}