import React from "react";
import useHalls from "../../../query/hooks/useHalls";
import {
    Box,
    Card,
    CardActions,
    CardContent,
    CardHeader,
    CardMedia,
    Grid,
    LinearProgress,
    Typography
} from "@mui/material";
import ErrorView from "../ErrorView";
import {BASE_URL, SERVER_ERROR} from "../../../utils/constans";
import Button from "@mui/material/Button";

export default function HallsView() {
    const halls = useHalls();

    return halls.isLoading ? <LinearProgress /> : halls.isError ? <ErrorView status={SERVER_ERROR} /> : (
        <Box sx={{mt: {xs: 2, md: 7}, mb: 5}}>
            <Typography variant="h2" component="h1" gutterBottom>
                Sale
            </Typography>
            <Typography variant="h5" component="h2" gutterBottom>
                Aby uprawianie Twojej ulubionej dyscypliny sportu było jak najprzyjemniesze spojrz na sale dostępne w naszym obiekcie.
                Każdy znajdzie tu coś dla siebie - zarówmo amatorzy jak i profesjonaliści.
            </Typography>
            <Grid container sx={{mt: 1, mb: 5}} spacing={4} alignItems="stretch">
                {halls.data.map(hall => (
                    <Grid item xs={12} md={6} lg={4}>
                        <Card>
                            <CardHeader title={hall.name} subheader={`Sala numer ${hall.number}`} />
                            <CardMedia
                                component="img"
                                image={BASE_URL + hall.imageUrl}
                                alt={hall.name}
                            />
                            <CardContent>
                                <Typography variant="body2" color="text.secondary">
                                    {hall.description}
                                </Typography>
                            </CardContent>
                            <CardActions>
                                <Button size="small">Kup bilet</Button>
                            </CardActions>
                        </Card>
                    </Grid>
                ))}
            </Grid>
        </Box>
    )
}