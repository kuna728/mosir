import {Box, LinearProgress, Stack, Typography} from "@mui/material";
import React from "react";
import useCoaches from "../../../query/hooks/useCoaches";
import ErrorView from "../ErrorView";
import {SERVER_ERROR} from "../../../utils/constans";
import Coach from "./components/Coach";


export default function CoachesView() {

    const coaches = useCoaches();

    return coaches.isLoading ? <LinearProgress /> : coaches.isError ? <ErrorView status={SERVER_ERROR} /> : (
        <Box>
            <Box sx={{mt: {sx: 2, md: 7}}}>
                <Typography variant="h2" component="h1" gutterBottom>
                    Trenerzy
                </Typography>
                <Typography variant="h5" component="h2" gutterBottom>
                    Zapoznaj się z naszą wykwalifikowaną kadrom trenerską.
                </Typography>
                <Stack spacing={4} sx={{mt: 5, mb: 5}}>
                    {coaches.data.map(coach => <Coach {...coach} />)}
                </Stack>
            </Box>
        </Box>
    )
}