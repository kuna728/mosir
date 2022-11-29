import React from "react";
import {
    Box,
    Card, CardActions,
    CardContent,
    CardMedia,
    Grid, LinearProgress,
    Typography
} from "@mui/material";
import useActivities from "../../query/hooks/useActivities";
import {BASE_URL, SERVER_ERROR} from "../../utils/constans";
import ErrorView from "./ErrorView";
import Button from "@mui/material/Button";

export default function ActivitiesView() {

    const activities = useActivities();

    return activities.isLoading ? <LinearProgress /> : activities.isError ? <ErrorView status={SERVER_ERROR} /> : (
        <Box>
            <Box sx={{mt: {xs: 2, md: 7}}}>
                <Typography variant="h2" component="h1" gutterBottom>
                    Sporty
                </Typography>
                <Typography variant="h5" component="h2" gutterBottom>
                    Zapoznaj się z ofertą sportową mosiru.
                </Typography>
                <Grid container sx={{mt: 1, mb: 5}} spacing={4}>
                    {activities.data.map((activity) => (
                        <Grid item xs={12} md={6} xl={3} key={activity.name}>
                            <Card sx={{minHeight: 300}}>
                                <CardMedia
                                    component="img"
                                    image={BASE_URL + activity.imageUrl}
                                    alt={activity.name}
                                />
                                <CardContent>
                                    <Typography gutterBottom variant="h5" component="div">
                                        {activity.name}
                                    </Typography>
                                    <Typography variant="body2" color="text.secondary">
                                        {activity.description}
                                    </Typography>
                                </CardContent>
                                <CardActions>
                                    <Button size="small">Trenerzy</Button>
                                    <Button size="small">Bilety</Button>
                                </CardActions>
                            </Card>
                        </Grid>
                    ))}
                </Grid>
            </Box>
        </Box>
    )
}