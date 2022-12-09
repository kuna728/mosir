import {Grid, Paper, Typography} from "@mui/material";
import {BASE_URL} from "../../../../utils/constans";
import {parseStringArray} from "../../../../utils/stringUtils";

export default function Coach({firstName, lastName, dateOfBirth, email, phoneNumber, activityTypes, imageUrl}) {

    return (
        <Paper sx={{padding: 2}}>
            <Grid container spacing={5} justifyContent="center">
                <Grid item xs="auto">
                    <img alt={`${firstName} ${lastName}`} src={BASE_URL + imageUrl} style={{borderRadius: 150}}/>
                </Grid>
                <Grid item xs={12} md={9}>
                    <Grid container spacing={5}>
                        <Grid item>
                            <Typography variant="subtitle1" gutterBottom>
                                Imię i nazwisko
                            </Typography>
                            <Typography variant="h5" gutterBottom sx={{fontWeight: "bolder"}} >
                                {firstName} {lastName}
                            </Typography>
                        </Grid>
                        <Grid item>
                            <Typography variant="subtitle1" gutterBottom>
                                Data urodzenia
                            </Typography>
                            <Typography variant="h5" gutterBottom>
                                {dateOfBirth}
                            </Typography>
                        </Grid>
                        <Grid item>
                            <Typography variant="subtitle1" gutterBottom>
                                Adres email
                            </Typography>
                            <Typography variant="h5" gutterBottom >
                                {email}
                            </Typography>
                        </Grid>
                        <Grid item>
                            <Typography variant="subtitle1" gutterBottom>
                                Numer telefonu
                            </Typography>
                            <Typography variant="h5" gutterBottom>
                                {phoneNumber}
                            </Typography>
                        </Grid>
                        <Grid item>
                            <Typography variant="subtitle1" gutterBottom>
                                Trenowane sporty
                            </Typography>
                            <Typography variant="h5" gutterBottom>
                                {parseStringArray(activityTypes)}
                            </Typography>
                        </Grid>
                    </Grid>
                </Grid>
            </Grid>
        </Paper>
    )
}