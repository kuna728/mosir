import {Avatar, Grid, Paper, Stack, Typography} from "@mui/material";
import {BASE_URL} from "../../../../utils/constans";
import {parseStringArray} from "../../../../utils/stringUtils";

export default function Coach({firstName, lastName, dateOfBirth, email, phoneNumber, activityTypes, imageUrl}) {

    return (
        <Paper sx={{padding: 2}}>
            <Stack direction="row" spacing={5}>
                <img src={BASE_URL + imageUrl} style={{borderRadius: 150}}/>
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
                            Adres email
                        </Typography>
                        <Typography variant="h5" gutterBottom>
                            {email}
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
            </Stack>
        </Paper>
    )
}