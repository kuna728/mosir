import {
    Backdrop,
    Box,
    Button,
    CircularProgress,
    Grid,
    MenuItem,
    Select,
    Stack,
    Typography, useMediaQuery,
    useTheme
} from "@mui/material";
import React from "react";
import {useNavigate} from "react-router-dom";
import {AuthContext} from "../../../../auth/AuthContext";
import useBuyTicket from "../../../../query/hooks/useBuyTicket";
import tokenFetcher from "../../../../query/fetchers/tokenFetcher";
import {useQueryClient} from "@tanstack/react-query";

export default function PaymentStep({
        tickets, membershipCards, activities,
        activity, ticket, discount, setBlockStepper,
        resetForm
}) {

    const [paymentMethod, setPaymentMethod] = React.useState('');
    const [waitingForPayment, setWaitingForPayment] = React.useState(0);
    const [paymentSuccess, setPaymentSuccess] = React.useState(null);

    const theme = useTheme();
    const stackDirection = useMediaQuery(theme.breakpoints.down("md")) ? "column" : "row";

    const navigate = useNavigate();

    const auth = React.useContext(AuthContext);
    const queryClient = useQueryClient();

    const buildResumeString = () => {
        let activityString = activities.find(a => a.slug === activity).name;
        let id = parseInt(ticket.substring(2));
        let ticketToBuy = ticket.startsWith('s') ? tickets.find(t => t.id === id)
            : membershipCards.find(m => m.id === id);
        let ticketString = ticket.startsWith('s') ? 'bilet jednorazowy' : `karnet ${ticketToBuy.totalUsages} wejść`;
        let ticketDiscount = ticketToBuy.discountTypes.find(d => d.id === discount);
        let discountString = `${ticketDiscount.name} (${ticketDiscount.value*100}%)`
        return [activityString, ticketString, discountString].join(', ')
    }

    const buildFinalPrice = () => {
        let id = parseInt(ticket.substring(2));
        let ticketToBuy = ticket.startsWith('s') ? tickets.find(t => t.id === id)
            : membershipCards.find(m => m.id === id);
        return ticketToBuy.price;
    }

    const handleSubmitButtonClick = () => {
        setWaitingForPayment(1);
        setTimeout(() => {
            const ticketType = ticket.startsWith('s') ? 'SINGLE' : 'MULTI';
            const ticketId = parseInt(ticket.substring(2));
            tokenFetcher("/api/ticket", auth.token, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ticketType, ticketId, discountId: discount})
            }).then(() => {
                setWaitingForPayment(2);
                setPaymentSuccess(true);
                queryClient.invalidateQueries(['user_tickets']);
            }).catch(e => {
                setWaitingForPayment(2);
                setPaymentSuccess(false);
            })
        }, 3000)
    }

    const handleResetButtonClick = () => {
        resetForm();
        setPaymentMethod('');
        setWaitingForPayment(0);
        setPaymentSuccess(null);
    }

    const handleRetryButtonClick = () => {
        setBlockStepper(false);
        setWaitingForPayment(0);
        setPaymentSuccess(null);
    }

    return activity && ticket && discount && (
        <Box>
            <Box sx={{display: waitingForPayment <= 1 ? 'block' : 'none'}}>
                <Grid container spacing={3}>
                    <Grid item xs={12} md={6}>
                        <Typography variant="body2">
                            Kupujesz
                        </Typography>
                        <Typography variant="h5">{buildResumeString()}</Typography>
                    </Grid>
                    <Grid item xs={12} md={6}>
                        <Typography variant="body2">
                            Do zapłaty
                        </Typography>
                        <Typography variant="h3">{buildFinalPrice()} PLN</Typography>
                    </Grid>
                    <Grid item xs={12}>
                        <Typography variant="body2">
                            Forma płatności
                        </Typography>
                        <Select fullWidth value={paymentMethod} onChange={e => setPaymentMethod(e.target.value)}>
                            <MenuItem value={1}>Przelewy24</MenuItem>
                            <MenuItem value={2}>Karta płatnicza online</MenuItem>
                            <MenuItem value={3}>BLIK</MenuItem>
                        </Select>
                    </Grid>
                    <Grid item xs={12}>
                        <Typography variant="body2">
                            Sposób dostawy
                        </Typography>
                        <Select fullWidth disabled value={1}>
                            <MenuItem value={1}>Przesyłka elektroniczna (e-mail)</MenuItem>
                        </Select>
                    </Grid>
                    <Grid item xs={12}>
                        <Button variant="contained" size="large" fullWidth disabled={paymentMethod === ''} onClick={handleSubmitButtonClick}>
                            Kupuje i płace
                        </Button>
                    </Grid>
                </Grid>
            </Box>
            <Box sx={{display: waitingForPayment === 2 ? 'block' : 'none'}}>
                {paymentSuccess ? <>
                    <Typography variant="h4">Udało się!</Typography>
                    <Typography variant="subtitle1">
                        Płatność zostala pomyślnie przetworzona. Możesz już pobrać swój bilet.
                    </Typography>
                    <Stack direction={stackDirection} spacing={1} sx={{mt: 2}}>
                    <Button variant="contained" onClick={() => navigate('/moje-bilety')}>Do listy biletów</Button>
                    <Button variant="contained">Pobierz bilet</Button>
                    <Button variant="contained">Pobierz fakture</Button>
                    <Button variant="contained" onClick={handleResetButtonClick}>Kup ponownie</Button>
                    </Stack>
                </> : <>
                    <Typography variant="h4">Coś poszło nie tak.</Typography>
                    <Typography variant="subtitle1">
                    Płatność nie mogła zostać przetworzona. Spróbuj ponownie później lub skontaktuj się z nami.
                    </Typography>
                    <Stack direction={stackDirection} spacing={1} sx={{mt: 2}}>
                    <Button variant="contained" onClick={() => navigate('/moje-bilety')}>Do listy biletów</Button>
                    <Button variant="contained" onClick={handleRetryButtonClick}>Spróbuj ponownie</Button>
                    </Stack>
                </>}

            </Box>
            <Backdrop
                sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
                open={waitingForPayment === 1}
            >
                <CircularProgress color="inherit" />
            </Backdrop>
        </Box>

    )
}