import React from "react";
import {Stack, Typography} from "@mui/material";
import {capitalizeFirstLetter} from "../../../../utils/stringUtils";

export default function TicketDetails({ticket, isMembershipCard}) {

    return (
        <>
            <Typography variant="body2">
                {isMembershipCard ? `Karnet na ${ticket.totalUsages} wejść` : "Bilet jednorazowy"}
            </Typography>
            <Typography variant="h3">{ticket.price},-</Typography>
            <Stack>
                {ticket.discountTypes.filter(discountType => discountType.name !== "normalny").map(discountType => (
                    <Typography variant="body1">
                        {capitalizeFirstLetter(discountType.name)}&nbsp;
                        <b>{parseFloat(Number(discountType.value * ticket.price).toFixed(2))},-</b>&nbsp;
                        ({discountType.value * 100}%)
                    </Typography>
                ))}
            </Stack>
        </>
    )
}