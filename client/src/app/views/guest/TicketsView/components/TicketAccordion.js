import React from "react";
import {Accordion, AccordionDetails, AccordionSummary, Grid, Typography} from "@mui/material";
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import TicketDetails from "./TicketDetails";

export default function TicketAccordion({ticket, membershipCards}) {

    return (
        <Accordion>
            <AccordionSummary
                expandIcon={<ExpandMoreIcon />}
                aria-controls="panel2a-content"
                id="panel2a-header"
            >
                <Typography fontStyle="italic" sx={{letterSpacing: 2}}>{ticket.activityType}</Typography>
            </AccordionSummary>
            <AccordionDetails>
                <Grid container spacing={5}>
                    <Grid item xs={12} md={4}>
                        <TicketDetails ticket={ticket} />
                    </Grid>
                    {membershipCards.map(membershipCard => (
                        <Grid item xs={12} md={4} key={membershipCard.id}>
                            <TicketDetails ticket={membershipCard} isMembershipCard />
                        </Grid>
                    ))}
                </Grid>
            </AccordionDetails>
        </Accordion>
    )
}