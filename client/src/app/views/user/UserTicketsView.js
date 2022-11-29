import useUserTickets from "../../query/hooks/useUserTickets";
import {AuthContext} from "../../auth/AuthContext";
import {
    Box, Button, IconButton,
    LinearProgress,
    Paper, Tab,
    Table,
    TableBody,
    TableContainer,
    TableHead,
    TableRow, Tabs, Tooltip,
    Typography
} from "@mui/material";
import ReceiptIcon from '@mui/icons-material/Receipt';
import FileDownloadIcon from '@mui/icons-material/FileDownload';
import RefreshIcon from '@mui/icons-material/Refresh';
import ErrorView from "../guest/ErrorView";
import {SERVER_ERROR} from "../../utils/constans";
import React from "react";
import StyledTableCell from "../../components/StyledTableCell";
import StyledTableRow from "../../components/StyledTableRow";
import {useNavigate} from "react-router-dom";
import moment from "moment";
import {useQueryClient} from "@tanstack/react-query";
import downloadFile from "../../utils/downloadFile";

export default function UserTicketsView() {
    const auth = React.useContext(AuthContext);
    const userTickets = useUserTickets(auth);
    const navigate = useNavigate();
    const [openTab, setOpenTab] = React.useState(0);
    const queryClient = useQueryClient();

    const refresh = () => {
        queryClient.invalidateQueries(['user_tickets']);
    }

    return userTickets.isLoading ? <LinearProgress /> : userTickets.isError ? <ErrorView status={SERVER_ERROR} /> : (
        <Box sx={{mt: {xs: 2, md: 7}, mb: 5}}>
            <Typography variant="h2" component="h1" gutterBottom>
                Moje bilety
            </Typography>
            <Button size="large" variant="contained" sx={{mt: 1, mb: 3, mr: 2}} onClick={() => navigate("/kup-bilet")}>
                Kup bilet
            </Button>
            <Button size="large" variant="contained" sx={{mt: 1, mb: 3}} startIcon={<RefreshIcon />} onClick={refresh}>
                Odśwież
            </Button>
            <Box sx={{ borderBottom: 1, borderColor: 'divider', mb: 1 }}>
                <Tabs value={openTab} onChange={(e, newValue) => setOpenTab(newValue)}>
                    <Tab label="Bilety jednorazowe" />
                    <Tab label="Karnety" />
                </Tabs>
            </Box>
            <Box sx={{display: openTab === 0 ? 'block' : 'none'}}>
                <TableContainer component={Paper}>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <StyledTableCell>Sport</StyledTableCell>
                                <StyledTableCell align="right">Cena</StyledTableCell>
                                <StyledTableCell align="right">Data zakupu</StyledTableCell>
                                <StyledTableCell align="right">Wykorzystany</StyledTableCell>
                                <StyledTableCell align="right">Akcje</StyledTableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {userTickets.data.tickets.map(ticket => (
                                <StyledTableRow key={ticket.id} >
                                    <StyledTableCell component="th" scope="row">
                                        {ticket.activityType}
                                    </StyledTableCell>
                                    <StyledTableCell align="right">
                                        {ticket.price}
                                    </StyledTableCell>
                                    <StyledTableCell align="right">
                                        {moment(ticket.purchasedAt).format('D MMMM YYYY')}
                                    </StyledTableCell>
                                    <StyledTableCell align="right">
                                        {ticket.used ? "Tak" : "Nie"}
                                    </StyledTableCell>
                                    <StyledTableCell align="right">
                                        <Tooltip title="Pobierz fakture">
                                            <IconButton color="primary" onClick={() => downloadFile('single', ticket.id, 'invoice', auth.token)}>
                                                <ReceiptIcon />
                                            </IconButton>
                                        </Tooltip>
                                        <Tooltip title="Pobierz bilet">
                                            <IconButton color="primary" onClick={() => downloadFile('single', ticket.id, 'ticket', auth.token)}>
                                                <FileDownloadIcon />
                                            </IconButton>
                                        </Tooltip>
                                    </StyledTableCell>
                                </StyledTableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Box>
            <Box sx={{display: openTab === 1 ? 'block' : 'none'}}>
                <TableContainer component={Paper}>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <StyledTableCell>Sport</StyledTableCell>
                                <StyledTableCell align="right">Cena</StyledTableCell>
                                <StyledTableCell align="right">Data zakupu</StyledTableCell>
                                <StyledTableCell align="right">Data ważności</StyledTableCell>
                                <StyledTableCell align="right">Liczba użyć</StyledTableCell>
                                <StyledTableCell align="right">Akcje</StyledTableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {userTickets.data.membershipCards.map(membershipCard => (
                                <StyledTableRow key={membershipCard.id} >
                                    <StyledTableCell component="th" scope="row">
                                        {membershipCard.activityType}
                                    </StyledTableCell>
                                    <StyledTableCell align="right">
                                        {membershipCard.price}
                                    </StyledTableCell>
                                    <StyledTableCell align="right">
                                        {moment(membershipCard.purchasedAt).format('D MMMM YYYY')}
                                    </StyledTableCell>
                                    <StyledTableCell align="right">
                                        {moment(membershipCard.validTill).format('D MMMM YYYY')}
                                    </StyledTableCell>
                                    <StyledTableCell align="right">
                                        {`${membershipCard.numberOfUsages}/${membershipCard.totalUsages}`}
                                    </StyledTableCell>
                                    <StyledTableCell align="right">
                                        <Tooltip title="Pobierz fakture">
                                            <IconButton color="primary"
                                                        onClick={() => downloadFile('multi', membershipCard.id, 'invoice', auth.token)}
                                            >
                                                <ReceiptIcon />
                                            </IconButton>
                                        </Tooltip>
                                        <Tooltip title="Pobierz bilet">
                                            <IconButton color="primary"
                                                        onClick={() => downloadFile('multi', membershipCard.id, 'ticket', auth.token)}
                                            >
                                                <FileDownloadIcon />
                                            </IconButton>
                                        </Tooltip>
                                    </StyledTableCell>
                                </StyledTableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Box>
        </Box>
    )

}