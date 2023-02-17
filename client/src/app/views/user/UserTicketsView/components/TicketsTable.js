import {IconButton, Paper, Table, TableBody, TableContainer, TableHead, TableRow, Tooltip} from "@mui/material";
import StyledTableCell from "../../../../components/StyledTableCell";
import StyledTableRow from "../../../../components/StyledTableRow";
import moment from "moment/moment";
import downloadFile from "../../../../utils/downloadFile";
import ReceiptIcon from "@mui/icons-material/Receipt";
import FileDownloadIcon from "@mui/icons-material/FileDownload";
import React from "react";

export default function TicketsTable({tickets, type, token}) {

    return (
        <TableContainer component={Paper}>
            <Table>
                <TableHead>
                    <TableRow>
                        <StyledTableCell>Sport</StyledTableCell>
                        <StyledTableCell align="right">Cena</StyledTableCell>
                        <StyledTableCell align="right">Data zakupu</StyledTableCell>
                        <StyledTableCell align="right">{type === 'single' ? 'Wykorzystany' : 'Liczba użyć'}</StyledTableCell>
                        <StyledTableCell align="right">Akcje</StyledTableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {tickets.map(ticket => (
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
                                {type == 'single' ? (ticket.used ? "Tak" : "Nie") : `${ticket.numberOfUsages}/${ticket.totalUsages}`}
                            </StyledTableCell>
                            <StyledTableCell align="right">
                                <Tooltip title="Pobierz fakture">
                                    <IconButton color="primary" onClick={() => downloadFile(type, ticket.id, 'invoice', token)}>
                                        <ReceiptIcon />
                                    </IconButton>
                                </Tooltip>
                                <Tooltip title="Pobierz bilet">
                                    <IconButton color="primary" onClick={() => downloadFile(type, ticket.id, 'ticket', token)}>
                                        <FileDownloadIcon />
                                    </IconButton>
                                </Tooltip>
                            </StyledTableCell>
                        </StyledTableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    )
}