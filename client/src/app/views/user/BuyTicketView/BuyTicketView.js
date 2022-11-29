import React from "react";
import {AuthContext} from "../../../auth/AuthContext";
import useUserDetails from "../../../query/hooks/useUserDetails";
import {
    Box,
    Button,
    LinearProgress,
    Step,
    StepLabel,
    Stepper,
    Typography,
    useMediaQuery,
    useTheme
} from "@mui/material";
import TicketDetailsStep from "./components/TicketDetailsStep";
import UserDetailsStep from "./components/UserDetailsStep";
import PaymentStep from "./components/PaymentStep";
import useTickets from "../../../query/hooks/useTickets";
import {isError, isLoading} from "../../../utils/queryUtils";
import ErrorView from "../../guest/ErrorView";
import {SERVER_ERROR} from "../../../utils/constans";
import useMembershipCards from "../../../query/hooks/useMembershipCards";
import useActivities from "../../../query/hooks/useActivities";

export default function BuyTicketView() {

    const auth = React.useContext(AuthContext);
    const theme = useTheme();

    const userDetails = useUserDetails(auth);
    const tickets = useTickets();
    const membershipCards = useMembershipCards();
    const activities = useActivities();

    const [activeStep, setActiveStep] = React.useState(0);
    const [blockStepper, setBlockStepper] = React.useState(false);
    const [activity, setActivity] = React.useState('');
    const [ticket, setTicket] = React.useState('');
    const [discount, setDiscount] = React.useState('');

    const stepperOrientation = useMediaQuery(theme.breakpoints.down("md")) ? "vertical" : "horizontal";

    const resetForm = () => {
        setActiveStep(0);
        setBlockStepper(false);
        setActivity('');
        setTicket('');
        setDiscount('');
    }

    return isLoading(userDetails, tickets, membershipCards, activities) ? <LinearProgress /> :
        isError(userDetails, tickets, membershipCards, activities) ? <ErrorView status={SERVER_ERROR} /> : (
            <Box sx={{mt: {xs: 2, md: 7}, mb: 5}}>
                <Typography variant="h2" component="h1" gutterBottom>
                    Kup bilet
                </Typography>
                <Stepper activeStep={activeStep} orientation={stepperOrientation}>
                    <Step>
                        <StepLabel>Wybór biletu</StepLabel>
                    </Step>
                    <Step>
                        <StepLabel>Dane kupującego</StepLabel>
                    </Step>
                    <Step>
                        <StepLabel>Finalizacja płatności</StepLabel>
                    </Step>
                </Stepper>
                <Box sx={{mt: 5}}>
                    <Box sx={{display: activeStep === 0 ? 'block' : 'none'}}>
                        <TicketDetailsStep tickets={tickets.data}
                                           membershipCards={membershipCards.data}
                                           activities={activities.data}
                                           activity={activity} setActivity={setActivity}
                                           ticket={ticket} setTicket={setTicket}
                                           discount={discount} setDiscount={setDiscount}
                        />
                    </Box>
                    <Box sx={{display: activeStep === 1 ? 'block' : 'none'}}>
                        <UserDetailsStep userDetails={userDetails.data} />
                    </Box>
                    <Box sx={{display: activeStep === 2 ? 'block' : 'none'}}>
                        <PaymentStep tickets={tickets.data}
                                     membershipCards={membershipCards.data}
                                     activities={activities.data}
                                     activity={activity}
                                     ticket={ticket}
                                     discount={discount}
                                     setBlockStepper={setBlockStepper}
                                     resetForm={resetForm}
                        />
                    </Box>
                    <Box sx={{mt: 5, display: 'flex', justifyContent: 'space-between'}}>
                        <Button disabled={blockStepper || activeStep === 0} onClick={() => setActiveStep(activeStep-1)}>
                            Powrót
                        </Button>
                        <Button disabled={blockStepper || activeStep === 2 || discount === ''} onClick={() => setActiveStep(activeStep+1)}>
                            Dalej
                        </Button>
                    </Box>
                </Box>
            </Box>
    )
}