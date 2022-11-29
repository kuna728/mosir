import React from "react";
import useTickets from "../../../query/hooks/useTickets";
import useMembershipCards from "../../../query/hooks/useMembershipCards";
import {Box, Button, LinearProgress, Typography} from "@mui/material";
import ErrorView from "../ErrorView";
import {SERVER_ERROR} from "../../../utils/constans";
import {isError, isLoading} from "../../../utils/queryUtils";
import TicketAccordion from "./components/TicketAccordion";
import {useNavigate} from "react-router-dom";

export default function TicketsView() {

    const navigate = useNavigate();

    const tickets = useTickets();
    const membershipCards = useMembershipCards();

    const getActivityTypes = () => [...new Set(tickets.data.map(ticket => ticket.activityType)
        .concat(membershipCards.data.map(membershipCard => membershipCard.activityType)))]

    return isLoading(tickets, membershipCards) ? <LinearProgress /> :
        isError(tickets, membershipCards) ? <ErrorView status={SERVER_ERROR} /> : (
            <Box sx={{mt: {xs: 2, md: 7}, mb: 5}}>
                <Typography variant="h2" component="h1" gutterBottom>
                    Bilety
                </Typography>
                <Typography variant="h5" component="h2" gutterBottom>
                    Zapoznaj się z cennikiem biletów oraz karnetów. Możesz kupić bilet online, jednak musisz posiadać konto w serwisie.
                </Typography>
                <Button size="large" variant="contained" sx={{mt: 1, mb: 3}} onClick={() => navigate("/kup-bilet")}>
                    Kup bilet
                </Button>
                {getActivityTypes().map(activityType => (
                    <TicketAccordion key={activityType.name} ticket={tickets.data.filter(ticket => ticket.activityType === activityType)[0]}
                                     membershipCards={membershipCards.data.filter(membershipCard => membershipCard.activityType === activityType)}
                    />
                ))}
            </Box>
    )
}