import useUserSingleTickets from "../../../query/hooks/useUserSingleTickets";
import {AuthContext} from "../../../auth/AuthContext";
import {
    Box, Button, CircularProgress, IconButton,
    LinearProgress, Pagination,
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
import ErrorView from "../../guest/ErrorView";
import {SERVER_ERROR} from "../../../utils/constans";
import React from "react";
import StyledTableCell from "../../../components/StyledTableCell";
import StyledTableRow from "../../../components/StyledTableRow";
import {useNavigate} from "react-router-dom";
import moment from "moment";
import {useQueryClient} from "@tanstack/react-query";
import downloadFile from "../../../utils/downloadFile";
import TicketsTable from "./components/TicketsTable";
import useUserMultiTickets from "../../../query/hooks/useUserMultiTickets";
import {isError, isLoading} from "../../../utils/queryUtils";
import LoadingButton from "@mui/lab/LoadingButton";

export default function UserTicketsView() {
    const auth = React.useContext(AuthContext);
    const [openTab, setOpenTab] = React.useState(0);
    const [pageSingle, setPageSingle] = React.useState(1);
    const [pageMulti, setPageMulti] = React.useState(1);
    const [isRefreshing, setRefreshing] = React.useState(false);

    const userSingleTickets = useUserSingleTickets(pageSingle - 1, auth);
    const userMultiTickets = useUserMultiTickets(pageMulti - 1, auth);
    const navigate = useNavigate();
    const queryClient = useQueryClient();

    const refresh = () => {
        setRefreshing(true);
        queryClient.invalidateQueries(['user_single_tickets', 'user_multi_tickets']).then(() => setRefreshing(false));
    }

    return isLoading(userSingleTickets, userMultiTickets) ? <LinearProgress /> : isError(userSingleTickets, userMultiTickets) ? <ErrorView status={SERVER_ERROR} /> : (
        <Box sx={{mt: {xs: 2, md: 7}, mb: 5}}>
            <Typography variant="h2" component="h1" gutterBottom>
                Moje bilety
            </Typography>
            <Button size="large" variant="contained" sx={{mt: 1, mb: 3, mr: 2}} onClick={() => navigate("/kup-bilet")}>
                Kup bilet
            </Button>
            <LoadingButton size="large" variant="contained" sx={{mt: 1, mb: 3}} startIcon={<RefreshIcon />} onClick={refresh} loading={isRefreshing}>
                Odśwież
            </LoadingButton>
            <Box sx={{ borderBottom: 1, borderColor: 'divider', mb: 1 }}>
                <Tabs value={openTab} onChange={(e, newValue) => setOpenTab(newValue)}>
                    <Tab label="Bilety jednorazowe" />
                    <Tab label="Karnety" />
                </Tabs>
            </Box>
            <Box sx={{display: openTab === 0 ? 'block' : 'none'}}>
                {userSingleTickets.data.total === 0 ? (
                    <Typography variant="h5" sx={{mt: 2}}>Nie masz jeszcze żadnych biletów.</Typography>
                ) : (
                    <>
                        <TicketsTable tickets={userSingleTickets.data.items} type='single' token={auth.token} />
                        {userSingleTickets.isFetching && <LinearProgress color="secondary"/>}
                        <Pagination sx={{mt: 2}} count={Math.ceil(userSingleTickets.data.total / userSingleTickets.data.size)}
                                    color="secondary" page={pageSingle} onChange={(event,value) => setPageSingle(value)}
                        />
                    </>
                )}

            </Box>
            <Box sx={{display: openTab === 1 ? 'block' : 'none'}}>
                {userMultiTickets.data.total === 0 ? (
                    <Typography variant="h5" sx={{mt: 2}}>Nie masz jeszcze żadnych karnetów.</Typography>
                ) : (
                    <>
                        <TicketsTable tickets={userMultiTickets.data.items} type='multi' token={auth.token} />
                        {userMultiTickets.isFetching && <LinearProgress color="secondary"/>}
                        <Pagination sx={{mt: 2}} count={Math.ceil(userMultiTickets.data.total / userMultiTickets.data.size)}
                                    color="secondary" page={pageMulti} onChange={(event,value) => setPageMulti(value)}
                        />
                    </>
                )}
            </Box>
        </Box>
    )

}