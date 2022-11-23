import React from "react";
import {Box, FormControl, InputLabel, MenuItem, Select, Stack, Typography} from "@mui/material";

export default function TicketDetailsStep({
        tickets, membershipCards, activities, activity,
        setActivity, ticket, setTicket, discount, setDiscount

}){

    const handleActivityChange = e => {
        setTicket('');
        setDiscount('');
        setActivity(e.target.value)
    }

    const handleTicketChange = e => {
        setDiscount('');
        setTicket(e.target.value)
    }

    const buildTicketTypes = () => {
        const activityName = activities.find(a => a.slug === activity).name;
        const ticketTypes = [];
        const singleUseTicket = tickets.find(t => t.activityType === activityName);
        if(singleUseTicket) {
            ticketTypes.push({id: `s_${singleUseTicket.id}`,
                name: "Jednorazowy", price: singleUseTicket.price})
        }
        membershipCards.filter(m => m.activityType === activityName).forEach(m => {
            ticketTypes.push({id: `m_${m.id}`,
                name: `Karnet ${m.totalUsages} użyć`, price: m.price})
        })
        return ticketTypes;
    }

    const buildDiscountTypes = () => {
        if(ticket.startsWith('s')) {
            const activityName = activities.find(a => a.slug === activity).name;
            return tickets.find(t => t.activityType === activityName).discountTypes;
        } else {
            const id = parseInt(ticket.substring(2));
            return membershipCards.find(m => m.id === id).discountTypes;
        }
    }

    const getTicketPrice = () => {
        let currentTicket;
        if(ticket.startsWith('s')) {
            const activityName = activities.find(a => a.slug === activity).name;
            currentTicket = tickets.find(t => t.activityType === activityName);
        } else {
            const id = parseInt(ticket.substring(2));
            currentTicket = membershipCards.find(m => m.id === id);
        }
        return currentTicket.price * currentTicket.discountTypes.find(d => d.id === discount).value;
    }

    return (
        <Stack spacing={4}>
            <FormControl fullWidth>
                <InputLabel>Rodzaj aktywności</InputLabel>
                <Select value={activity} onChange={handleActivityChange} label="Rodzaj aktywności">
                    {activities.map(a => <MenuItem key={a.slug} value={a.slug}>{a.name}</MenuItem> )}
                </Select>
            </FormControl>
            <FormControl fullWidth>
                <InputLabel>Typ biletu</InputLabel>
                <Select value={ticket} onChange={handleTicketChange} label="Typ biletu" disabled={activity===''}>
                    {activity && buildTicketTypes().map(t => <MenuItem key={t.id} value={t.id}>{t.name}</MenuItem> )}
                </Select>
            </FormControl>
            <FormControl fullWidth>
                <InputLabel>Rodzaj zniżki</InputLabel>
                <Select value={discount} onChange={e => setDiscount(e.target.value)} label="Rodzaj zniżki" disabled={ticket==='' }>
                    {activity && ticket && buildDiscountTypes().map(d =>
                        <MenuItem key={d.id} value={d.id}>
                            {d.name} ({d.value*100})%
                        </MenuItem>
                    )}
                </Select>
            </FormControl>
            {discount !== '' &&
                <Box>
                    <Typography variant="body2">
                        Do zapłaty
                    </Typography>
                    <Typography variant="h3">{getTicketPrice()} PLN</Typography>
                </Box>
            }
        </Stack>
    )
}
